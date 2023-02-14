package com.github.zeros.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.zeros.core.callback.DefaultCallback;
import com.github.zeros.core.callback.ICallback;
import com.github.zeros.core.worker.IWorker;
import com.github.zeros.core.worker.State;
import com.github.zeros.core.worker.WrokResult;
import com.github.zeros.util.TimerClock;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Wrapper<T, F> {

    private static final int FINISH = 1;
    private static final int ERROR = 2;
    private static final int WORKING = 3;
    private static final int INIT = 0;

    private String id;

    private T param;

    private IWorker<T, F> worker;

    private ICallback<T, F> callback;

    // 在自己后面的wrapper，有多少个就得开多少个线程
    // There are as many threads as there are wrappers behind you.
    private List<Wrapper<?, ?>> nextWrappers;

    /**
     * 依赖的wrappers，有两种情况
     *  1. 必须全部依赖完成才能执行自己
     *  2. 一个或者多个完成就可以执行自己
     *  通过must字段控制
     */
    private List<DependWrapper> dependWrappers;

    /**
     * 1: finish
     * 2: error
     * 3: working
     */
    private AtomicInteger state = new AtomicInteger(0);

    private volatile WrokResult<F> wrokResult = WrokResult.defaultResult();

    private Map<String, Wrapper<?, ?>> wrapperMap;

    /**
     * 是否在执行自己前，去校验nextWrappers的执行结果
     */
    private volatile boolean needCheckNextWrapperResult = true;

    private Wrapper(String id, IWorker<T, F> worker, T param, ICallback<T, F> callback) {
        if (worker == null) {
            throw new RuntimeException("async worker is null");
        }
        this.worker = worker;
        this.param = param;
        this.id = id;
        if (callback == null) {
            callback = new DefaultCallback<>();
        }
        this.callback = callback;
    }


    public void worker(ExecutorService executorService, long remainTime, Map<String, Wrapper<?, ?>> wrapperMap) {
        worker(executorService, null, remainTime, wrapperMap);
    }

    private void worker(ExecutorService executorService, Wrapper<?, ?> wrapper, long remainTime, Map<String, Wrapper<?, ?>> wrapperMap) {
        this.wrapperMap = wrapperMap;
        wrapperMap.put(id, this);
        long now = TimerClock.now();
        // 剩余时间小于0
        // Remaining time is less than 0
        if (remainTime <= 0) {
            if (failFast(INIT, null)) {
                nextTask(executorService, now, remainTime);
                return;
            }
        }
        if (getState() == FINISH || getState() == ERROR) {
            nextTask(executorService, now, remainTime);
            return;
        }

        // 执行前需要校验nextWrapper执行结果
        if (needCheckNextWrapperResult) {
            if (!checkNextWrapperResult()) {
                failFast(INIT, new Exception("该任务被跳过了"));
                nextTask(executorService, now, remainTime);
                return;
            }
        }

        // 没任何依赖
        if (dependWrappers == null || dependWrappers.size() == 0) {
            job();
            nextTask(executorService, now, remainTime);
            return;
        }

        /*
            有依赖两种情况
            1. 只有一个依赖
            2. 有多个依赖 比如 A B C -> D需要ABC执行完才行，比如A B C -> D 只需要ABC其中一个执行完成就行
         */
        if (dependWrappers.size() == 1) {
            // todo
        } else {

        }
    }

    /**
     * 执行自己job，具体执行是其他线程，判断超时在work线程
     */
    private void job() {
        wrokResult = doJob();
    }

    private WrokResult<F> doJob() {
        if (!checkIsNullResult()) {
            return wrokResult;
        }

        // 如果不是init状态，说明执行完毕或者正在执行中，避免重复执行
        if (!this.state.compareAndSet(INIT, WORKING)) {
            return wrokResult;
        }
        callback.listening();

        F value = worker.apply(param, wrapperMap);

        if (!this.state.compareAndSet(WORKING, FINISH)) {
            return wrokResult;
        }

        wrokResult.setState(State.SUCCESS);
        wrokResult.setResult(value);

        callback.complete(true, param, wrokResult);
        return wrokResult;
    }

    private void doDependsOneJob(Wrapper<?, ?> wrapper) {
        if (State.TIMEOUT == wrapper.getWrokResult().getState()) {
            wrokResult = defaultResult();
            failFast(INIT, null);
        } else if (State.EXCEPTION == wrapper.getWrokResult().getState()) {
            // todo 判断是否异常 是否需要failFast
        }
    }

    private int getState() {
        return state.get();
    }

    private void nextTask(ExecutorService executorService, long now, long remainTime) {
        long costTime = TimerClock.now() - now;
        if (nextWrappers == null) {
            return;
        }
        if (nextWrappers.size() == 1) {
            nextWrappers.get(0).worker(executorService, Wrapper.this, remainTime - costTime, wrapperMap);
            return;
        }
        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        nextWrappers.forEach(wrapper -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                    () -> wrapper.worker(executorService, Wrapper.this, remainTime - costTime, wrapperMap),
                    executorService);
            completableFutureList.add(future);
        });
        try {
            CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
                    .get(remainTime - costTime, TimeUnit.MICROSECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断自己下游链路上，是否存在执行完成或者还在执行的
     * 如果没有返回true，有就返回false
     */
    private boolean checkNextWrapperResult() {
        if (nextWrappers == null || nextWrappers.size() != 1) {
            return getState() == INIT;
        }
        Wrapper<?, ?> nextWrapper = nextWrappers.get(0);
        boolean state = nextWrapper.getState() == INIT;
        return state && nextWrapper.checkNextWrapperResult();
    }

    private boolean failFast(int expect, Exception e) {
        if (!this.state.compareAndSet(expect, ERROR)) {
            return false;
        }
        if (checkIsNullResult()) {
            if (e == null) {
                wrokResult = defaultResult();
            } else {
                wrokResult = defaultExResult(e);
            }
        }
        callback.complete(false, param, wrokResult);
        return true;
    }

    private boolean checkIsNullResult() {
        return State.DEFAULT == wrokResult.getState();
    }

    private WrokResult<F> defaultResult() {
        wrokResult.setState(State.TIMEOUT);
        wrokResult.setResult(worker.defautValue());
        return wrokResult;
    }

    private WrokResult<F> defaultExResult(Exception e) {
        wrokResult.setState(State.EXCEPTION);
        wrokResult.setResult(worker.defautValue());
        wrokResult.setException(e);
        return wrokResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void setNeedCheckNextWrapperResult(boolean needCheckNextWrapperResult) {
        this.needCheckNextWrapperResult = needCheckNextWrapperResult;
    }

    public WrokResult<F> getWrokResult() {
        return wrokResult;
    }

    public void setWrokResult(WrokResult<F> wrokResult) {
        this.wrokResult = wrokResult;
    }

    public static class Builder<K, V> {

        private String id = UUID.randomUUID().toString();

        private K param;

        private IWorker<K, V> worker;

        private ICallback<K, V> callback;

        private List<Wrapper<?, ?>> nextWrappers;

        private List<DependWrapper> dependWrappers;

        /**
         * 存储强依赖于自己的wrapper集合
         */
        private Set<Wrapper<?, ?>> selfWrappers;

        private boolean needCheckNextWrapperResult = true;

        public Builder<K, V> worker(IWorker<K, V> worker) {
            this.worker = worker;
            return this;
        }

        public Builder<K, V> param(K k) {
            this.param = k;
            return this;
        }

        public Builder<K, V> id(String id) {
            if (id != null) {
                this.id = id;
            }
            return this;
        }

        public Builder<K, V> callback(ICallback<K, V> callback) {
            this.callback = callback;
            return this;
        }

        @SafeVarargs
        public final Builder<K, V> deque(Wrapper<K, V>... wrappers) {
            if (wrappers == null) {
                return this;
            }
            Arrays.stream(wrappers).map(this::deque);
            return this;
        }

        public Builder<K, V> deque(Wrapper<K, V> wrapper) {
            return deque(wrapper, true);
        }

        public Builder<K, V> deque(Wrapper<?, ?> wrapper, boolean isMust) {
            if (wrapper == null) {
                return this;
            }
            DependWrapper dependWrapper = new DependWrapper(wrapper, isMust);
            if (dependWrappers == null) {
                dependWrappers = new ArrayList<>();
            }
            dependWrappers.add(dependWrapper);
            return this;
        }

        public Builder<K, V> next(Wrapper<?, ?> wrapper) {
            return next(wrapper, true);
        }

        public Builder<K, V> next(Wrapper<?, ?> wrapper, boolean isSelfMust) {
            if (nextWrappers == null) {
                nextWrappers = new ArrayList<>();
            }
            nextWrappers.add(wrapper);
            if (isSelfMust) {
                if (selfWrappers == null) {
                    selfWrappers = new HashSet<>();
                }
                selfWrappers.add(wrapper);
            }
            return this;
        }


        public Wrapper<K, V> build() {
            Wrapper<K, V> wrapper = new Wrapper<>(id, worker, param, callback);
            wrapper.setNeedCheckNextWrapperResult(needCheckNextWrapperResult);
            if (dependWrappers != null) {
                // todo
            }
            return wrapper;
        }
    }
}

package com.github.zeros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.zeros.core.Wrapper;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Asyncstarter {

    private static final ThreadPoolExecutor COMMON_POOL = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    private static ExecutorService executorService;

    public static boolean starter(long timeout, Wrapper<?, ?>... wrappers) throws ExecutionException, InterruptedException {
        return starter(timeout, COMMON_POOL, wrappers);
    }

    public static boolean starter(long timeout, ExecutorService executorService, Wrapper<?, ?>... wrappers) throws ExecutionException, InterruptedException {
        if (wrappers == null || wrappers.length == 0) {
            return false;
        }
        List<Wrapper<?, ?>> wrapperList = Arrays.stream(wrappers).collect(Collectors.toList());
        return starter(timeout, executorService, wrapperList);
    }

    public static boolean starter(long timeout, ExecutorService executorService, List<Wrapper<?, ?>> wrappers) throws ExecutionException, InterruptedException {
        if (wrappers == null || wrappers.size() == 0) {
            return false;
        }
        Asyncstarter.executorService = executorService;
        Map<String, Wrapper<?, ?>> allUseWrappers = new ConcurrentHashMap<>();
        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        wrappers.forEach(wrapper -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> wrapper.worker(executorService, timeout, allUseWrappers), executorService);
            future.whenComplete(((BiConsumer<Object, Throwable>) (o, throwable) -> System.out.println("wrapper: [" + wrapper.getId()+ "] 执行成功")));
            future.exceptionally(throwable -> {
                System.out.println("wrapper: [" + wrapper.getId() + "] 执行失败");
                return null;
            });
            completableFutureList.add(future);
        });
        try {
            CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[] {})).get(timeout, TimeUnit.MICROSECONDS);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

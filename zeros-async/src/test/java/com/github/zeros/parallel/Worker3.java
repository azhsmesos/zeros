package com.github.zeros.parallel;

import java.util.Map;

import com.github.zeros.core.Wrapper;
import com.github.zeros.core.callback.ICallback;
import com.github.zeros.core.worker.IWorker;
import com.github.zeros.core.worker.WrokResult;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Worker3 implements IWorker<String, String>, ICallback<String, String> {


    @Override
    public void complete(boolean success, String param, WrokResult<String> res) {
        System.out.println("state: " + res.getState() + ", res: " + res.getResult());
    }

    @Override
    public String apply(String object, Map<String, Wrapper<String, String>> stringWrapperMap) {
        return "--worker3--";
    }
}

package com.github.zeros.core;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class DependWrapper {

    private Wrapper<?, ?> dependWrapper;

    private boolean must = true;

    public Wrapper<?, ?> getDependWrapper() {
        return dependWrapper;
    }

    public void setDependWrapper(Wrapper<?, ?> dependWrapper) {
        this.dependWrapper = dependWrapper;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public DependWrapper(Wrapper<?, ?> dependWrapper, boolean must) {
        this.dependWrapper = dependWrapper;
        this.must = must;
    }

    public DependWrapper() {

    }
}

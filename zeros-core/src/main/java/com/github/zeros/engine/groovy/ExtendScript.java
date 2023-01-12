package com.github.zeros.engine.groovy;

import groovy.json.JsonSlurper;
import groovy.lang.Script;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public abstract class ExtendScript extends Script {

    public JsonSlurper json() {
        return new JsonSlurper();
    }

    public boolean sample(String no, int value) {
        if (no == null) {
            return false;
        }
        return Math.abs(no.hashCode() % 1000) <= value;
    }

}

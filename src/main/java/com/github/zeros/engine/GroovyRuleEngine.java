package com.github.zeros.engine;

import java.util.Map;

import groovy.lang.Binding;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class GroovyRuleEngine implements RuleEngine {

    @Override
    public Boolean eval(String ruleScript, Map<String, Object> data) {
        Binding binding = new Binding();
        data.forEach(binding::setProperty);
        // parse
//        Class<?> compile =
        return null;
    }

    @Override
    public <T> T evalString(String ruleScript, Map<String, Object> data) {
        return null;
    }
}

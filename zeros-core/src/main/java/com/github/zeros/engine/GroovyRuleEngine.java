package com.github.zeros.engine;

import java.util.Map;

import org.codehaus.groovy.runtime.InvokerHelper;

import com.github.zeros.engine.groovy.GroovyEvaluator;

import groovy.lang.Binding;
import groovy.lang.Script;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class GroovyRuleEngine implements RuleEngine {

    @Override
    public Boolean eval(String ruleScript, Map<String, Object> data) {
        Binding binding = new Binding();
        data.forEach(binding::setProperty);
        Class<?> compile = GroovyEvaluator.parse(ruleScript, true);
        Script script = InvokerHelper.createScript(compile, binding);
        return (Boolean) script.run();
    }

    @Override
    public <T> T evalString(String ruleScript, Map<String, Object> data) {
        Binding binding = new Binding();
        data.forEach(binding::setProperty);
        Class<?> compile = GroovyEvaluator.parse(ruleScript, true);
        Script script = InvokerHelper.createScript(compile, binding);
        return (T) script.run();
    }
}

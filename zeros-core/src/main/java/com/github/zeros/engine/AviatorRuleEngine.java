package com.github.zeros.engine;

import java.util.Map;

import com.github.zeros.engine.function.DeepJsonParseFunction;
import com.github.zeros.engine.function.ShallowJsonToListFunction;
import com.github.zeros.engine.function.ShallowJsonToMapFunction;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class AviatorRuleEngine implements RuleEngine {

    static {
        AviatorEvaluator.addFunction(new ShallowJsonToMapFunction());
        AviatorEvaluator.addFunction(new ShallowJsonToListFunction());
        AviatorEvaluator.addFunction(new DeepJsonParseFunction());
    }

    @Override
    public Boolean eval(String ruleScript, Map<String, Object> data) {
        Expression compiled = AviatorEvaluator.compile(ruleScript, true);
        return (Boolean) compiled.execute(data);
    }

    @Override
    public <T> T evalString(String ruleScript, Map<String, Object> data) {
        Expression compiled = AviatorEvaluator.compile(ruleScript, true);
        return (T) compiled.execute(data);
    }
}

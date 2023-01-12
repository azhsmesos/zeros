package com.github.zeros.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zeros.util.enums.ScriptType;
/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class RuleEngineUtil {

    public static String scriptValue(String key, String script, String value, String type) {
        Map<String, Object> env = new HashMap<>();
        if (type == null) {
            type = ScriptType.GROOVY.getType();
        }
        RuleEngine engine = RuleEngineFactory.getRuleEngine(type);
        env.put(key, value);
        Object o = engine.evalString(script, env);
        return (String) o;
    }

    public static List<Object> scriptValueList(String key, String script, String value, String type) {
        if (value == null || value.length() == 0) {
            return new ArrayList<>();
        }
        Map<String, Object> env = new HashMap<>();
        if (type == null) {
            type = ScriptType.GROOVY.getType();
        }
        RuleEngine engine = RuleEngineFactory.getRuleEngine(type);
        env.put(key, value);
        Object o = engine.evalString(script, env);
        return castList(o, Object.class);
    }

    private static <T> List<T> castList(Object obj, Class<T> clz) {
        List<T> res = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                res.add(clz.cast(o));
            }
        }
        return res;
    }
}

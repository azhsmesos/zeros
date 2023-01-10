package com.github.zeros.engine;

import java.util.HashMap;
import java.util.Map;

import com.github.zeros.util.enums.ScriptType;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class RuleEngineFactory {

    private static final Map<String, RuleEngine> RULE_ENGINE_MAP = new HashMap<>();

    static {
        RULE_ENGINE_MAP.put(ScriptType.AVIATOR.getType(), new AviatorRuleEngine());
    }

    public static RuleEngine getRuleEngine(String scriptType) {
        return RULE_ENGINE_MAP.get(scriptType);
    }
}

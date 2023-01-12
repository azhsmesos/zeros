package com.github.zeros.engine;

import java.util.Map;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public interface RuleEngine {

    Boolean eval(String ruleScript, Map<String, Object> data);

    <T> T evalString(String ruleScript, Map<String, Object> data);
}

package com.github.zeros.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.zeros.util.enums.ScriptType;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class AviatorRuleEngineTest {



    @Test
    public void testEval() {
        String jsonData = "{\"settle_params\":[{\"seller_account_id\":\"1573069417\","
                + "\"account_group_key\":\"MERCHANT_SELLER\",\"amount\":4500,\"receiver_params_post_position\":true,"
                + "\"goods_infos\":[{\"goods_name\":\"闪电购女装53\",\"goods_category\":\"T恤\",\"goods_unit_price\":4500,"
                + "\"goods_quantity\":1,\"goods_type\":\"PHY\",\"goods_id\":\"15508048965417\"}]}],"
                + "\"need_merge_pay\":false}";
        String key = "settle_params";
        String script = "json().parseText(out_settle_param)?.settle_params.collect{it.seller_account_id}";
        Map<String, Object> env = new HashMap<>();
        RuleEngine engine = RuleEngineFactory.getRuleEngine(ScriptType.AVIATOR.getType());
        env.put(key, jsonData);
        Object o = engine.evalString(script, env);
        Assert.assertEquals((String) o, "1573069417");
    }

    @Test
    public void testAviator() {
        String email = "killme2008@gmail.com";
        String script = "email=~/([w0-8]+)@w+[.w+]+/ ? $1 : 'unknow";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        RuleEngine engine = RuleEngineFactory.getRuleEngine(ScriptType.AVIATOR.getType());
        Object username = engine.evalString(script, env);
        Assert.assertEquals(username, "killme2008"); // killme2008
    }

}

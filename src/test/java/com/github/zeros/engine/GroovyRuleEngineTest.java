package com.github.zeros.engine;

import static com.github.zeros.engine.RuleEngineUtil.scriptValueList;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class GroovyRuleEngineTest {

    private Logger logger = LoggerFactory.getLogger(GroovyRuleEngineTest.class);

    @Test
    public void testEval() {
        String jsonData = "{\"settle_params\":[{\"seller_account_id\":\"1573069417\","
                + "\"account_group_key\":\"MERCHANT_SELLER\",\"amount\":4500,\"receiver_params_post_position\":true,"
                + "\"goods_infos\":[{\"goods_name\":\"闪电购女装53\",\"goods_category\":\"T恤\",\"goods_unit_price\":4500,"
                + "\"goods_quantity\":1,\"goods_type\":\"PHY\",\"goods_id\":\"15508048965417\"}]}],"
                + "\"need_merge_pay\":false}";
        String key = "out_settle_param";
        String script = "json().parseText(out_settle_param)?.settle_params.collect{it.seller_account_id}";
        List<Object> res = scriptValueList(key, script, jsonData, null);
        res.forEach(item -> Assert.assertEquals(String.valueOf(item), "1573069417"));
    }
}

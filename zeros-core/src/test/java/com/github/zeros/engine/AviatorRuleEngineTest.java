package com.github.zeros.engine;

import static com.github.zeros.engine.RuleEngineUtil.scriptValue;

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


    private String bankInfo = "{\n"
            + "    \"PROVIDER_TRADE_MODE\":\"UNION_PAY_SIGNED_PAY\",\n"
            + "    \"ORDER_PROMO_CODE\":\"ECom_bank_promo_random\",\n"
            + "    \"EXTEND_PARAM\":\"{\\\"zero_order\\\":false}\",\n"
            + "    \"CASHIER_LIMIT\":\"{\\\"hide\\\":false,\\\"show_list\\\":[],"
            + "\\\"disable_options\\\":[\\\"DISABLE_ALIPAY_PAP\\\",\\\"DISABLE_WECHAT_PAP\\\",\\\"DISABLE_HB_FQ\\\","
            + "\\\"DISABLE_ALIPAY_PAY_AFTER_USE\\\",\\\"DISABLE_WECHAT_PAY_AFTER_USE\\\"]}\",\n"
            + "    \"CHANNEL_RESPONSE\":{\n"
            + "        \"merchantId\":\"GM712131691323525399\",\n"
            + "        \"outOrderNo\":\"2223700150612650\",\n"
            + "        \"orderPayNo\":\"OPCORE649644279459114405\",\n"
            + "        \"corePayNo\":\"CPN391378550001778658\",\n"
            + "        \"payAmount\":19194,\n"
            + "        \"notifyTime\":1661392152897,\n"
            + "        \"providerExtInfo\":{\n"
            + "            \"provider\":\"UNION_PAY_BANK\",\n"
            + "            \"payment_method\":\"PAP\",\n"
            + "            \"provider_channel_type\":\"NORMAL\",\n"
            + "            \"provider_trade_no\":\"685681817285588611508\",\n"
            + "            \"provider_complete_time\":\"1661392152840\",\n"
            + "            \"provider_app_id\":\"8981119539906C9\",\n"
            + "            \"actual_pay_amount\":19184,\n"
            + "            \"discount_amount\":10,\n"
            + "            \"bank_token\":\"6251645388314820\",\n"
            + "            \"wechat_pre_sign\":false\n"
            + "        },\n"
            + "        \"providerChannelExtra\":{\n"
            + "            \"provider_channel_type\":\"NORMAL\"\n"
            + "        },\n"
            + "        \"providerProductCode\":\"PINGAN_JZB\",\n"
            + "        \"providerTradeMode\":\"UNION_PAY_SIGNED_PAY\",\n"
            + "        \"subPayNotifyParams\":[\n"
            + "            {\n"
            + "                \"orderSubPayNo\":\"OPCORE649644279459114405\",\n"
            + "                \"coreSubPayNo\":\"CSPN391410622862993094\",\n"
            + "                \"providerSubPayNo\":\"685681817285588611508\",\n"
            + "                \"subPayAmount\":19194,\n"
            + "                \"providerProductCode\":\"PINGAN_JZB\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"promoParam\":{\n"
            + "            \"promoCode\":\"UNION_BANK_RANDOM\",\n"
            + "            \"orderPromoCode\":\"ECom_bank_promo_random\"\n"
            + "        }\n"
            + "    },\n"
            + "    \"PROVIDER_EXT_INFO\":{\n"
            + "        \"provider\":\"UNION_PAY_BANK\",\n"
            + "        \"payment_method\":\"PAP\",\n"
            + "        \"provider_channel_type\":\"NORMAL\",\n"
            + "        \"provider_trade_no\":\"685681817285588611508\",\n"
            + "        \"provider_complete_time\":\"1661392152840\",\n"
            + "        \"provider_app_id\":\"8981119539906C9\",\n"
            + "        \"actual_pay_amount\":19184,\n"
            + "        \"discount_amount\":10,\n"
            + "        \"bank_token\":\"6251645388314820\",\n"
            + "        \"wechat_pre_sign\":false\n"
            + "    },\n"
            + "    \"GATEWAY_FUND_BILL\":{\n"
            + "        \"provider\":\"UNION_PAY_BANK\",\n"
            + "        \"amount\":19194,\n"
            + "        \"actual_pay_amount\":19184,\n"
            + "        \"payment_method\":\"PAP\",\n"
            + "        \"provider_trade_no\":\"685681817285588611508\",\n"
            + "        \"provider_channel_type\":\"NORMAL\",\n"
            + "        \"union_pay_bank_info\":\"{\\\"bank_card_tail_no\\\":\\\"3906\\\","
            + "\\\"bank_short_name\\\":\\\"招商银行\\\",\\\"bank_card_type\\\":\\\"CREDIT_CARD\\\"}\"\n"
            + "    },\n"
            + "    \"CLIENT_INFO\":\"{\\\"accountId\\\":\\\"1102467650\\\",\\\"remoteIp\\\":\\\"3748145417\\\","
            + "\\\"deviceId\\\":\\\"BC73D974-A219-4395-86CF-1CC8C9976069\\\",\\\"appver\\\":\\\"10.7.30.7506\\\","
            + "\\\"sysver\\\":16.0,\\\"mod\\\":\\\"iPhone13,2\\\",\\\"net\\\":\\\"MOBILE_4G\\\","
            + "\\\"ksLogId\\\":\\\"661392152007466990\\\",\\\"channel\\\":\\\"a\\\","
            + "\\\"serverTimestamp\\\":\\\"1661392152008\\\",\\\"serverInfo\\\":\\\"public-bjzey-c9-pay-kce-node58"
            + ".idczw.hb1.kwaidc.com/21162\\\",\\\"locale\\\":\\\"zh_CN_#Hans\\\",\\\"remotePort\\\":36024,"
            + "\\\"sid\\\":\\\"kuaishou.midground.api\\\",\\\"kspayClientSdkVersion\\\":\\\"2.5.35\\\","
            + "\\\"os\\\":\\\"16.0\\\",\\\"kpn\\\":\\\"KUAISHOU\\\",\\\"kpf\\\":\\\"IPHONE\\\","
            + "\\\"gid\\\":\\\"000000021c93435e\\\",\\\"requestUri\\\":\\\"/pay/order/app/trade/create_pay_order"
            + "\\\"}\",\n"
            + "    \"PRE_ORDER_CLIENT_INFO\":\"{}\",\n"
            + "    \"PRE_ORDER_ANTISPAM_RESULT\":\"{\\\"canPasswordFree\\\":true,\\\"canFq\\\":true,"
            + "\\\"canPayAfterUseAlipay\\\":true,\\\"canPayAfterUseWechat\\\":true,\\\"canUnionPayBank\\\":true,"
            + "\\\"canBankPwdByPass\\\":true,\\\"antispamState\\\":\\\"PASS\\\",\\\"riskLevel\\\":\\\"MID\\\"}\",\n"
            + "    \"IS_INTEGRATE_ORDER\":false,\n"
            + "    \"CORE_SUB_PAY_PARAM\":[\n"
            + "        {\n"
            + "            \"orderSubPayNo\":\"OPCORE649644279459114405\",\n"
            + "            \"coreSubPayNo\":\"CSPN391410622862993094\",\n"
            + "            \"providerSubPayNo\":\"685681817285588611508\",\n"
            + "            \"subPayAmount\":19194,\n"
            + "            \"providerProductCode\":\"PINGAN_JZB\"\n"
            + "        }\n"
            + "    ],\n"
            + "    \"IS_ANTISPAM_FQ_SUPPORT\":true,\n"
            + "    \"PROVIDER_PRODUCT\":\"PINGAN_JZB\"\n"
            + "}";

    private String simpleInfo = "{\n" +
            "  \"name\": \"aaa\",\n" +
            "  \"id\": 123,\n" +
            "  \"friend\": {\n" +
            "    \"name\": \"bbb\",\n" +
            "    \"sex\": \"girl\",\n" +
            "    \"parent\": {\n" +
            "      \"name\": \"ccc\",\n" +
            "      \"money\": 80.1,\n" +
            "      \"dog\": {\n" +
            "        \"name\": \"ddd\",\n" +
            "        \"isCat\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    public void testEval() {
        String key = "data";
        String script = "jsonStrToMap(jsonStrToMap(jsonStrToMap(data)[\"GATEWAY_FUND_BILL\"])[\"union_pay_bank_info\"])[\"bank_short_name\"]";
        String res = scriptValue(key, script, bankInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("招商银行", res);
    }

    @Test
    public void testDeepEvalSimple() {
        String key = "data";
        String script = "parseJson(data)[\"friend\"][\"parent\"][\"dog\"][\"name\"]";
        String res = scriptValue(key, script, simpleInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("ddd", res);
    }

    @Test
    public void testDeepEvalMid() {
        String key = "data";
        String script = "parseJson(data)[\"GATEWAY_FUND_BILL\"][\"union_pay_bank_info\"][\"bank_short_name\"]";
        String res = scriptValue(key, script, bankInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("招商银行", res);
    }

    @Test
    public void testDeepEvalMidWithScript() {
        String key = "data";
        String script = "let o1 = parseJson(data);\n" +
                "let o2 = o1[\"GATEWAY_FUND_BILL\"];\n" +
                "let o3 = o2[\"union_pay_bank_info\"];\n" +
                "return o3[\"bank_short_name\"];";
        String res = scriptValue(key, script, bankInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("招商银行", res);
    }

    @Test
    public void testEvalList() {
        String key = "data";
        String script = "jsonStrToMap(jsonStrToList(jsonStrToMap(jsonStrToMap(data)[\"CHANNEL_RESPONSE\"])[\"subPayNotifyParams\"])[0])[\"subPayAmount\"]";
        String res = scriptValue(key, script, bankInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("19194", res);
    }

    @Test
    public void testDeepEvalList() {
        String key = "data";
        String script = "str(parseJson(data)[\"CHANNEL_RESPONSE\"][\"subPayNotifyParams\"][0][\"subPayAmount\"])";
        String res = scriptValue(key, script, bankInfo, ScriptType.AVIATOR.getType());
        Assert.assertEquals("19194", res);
    }

    @Test
    public void testAviator() {
        String email = "killme2008@gmail.com";
        String script = " email =~ /([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        RuleEngine engine = RuleEngineFactory.getRuleEngine(ScriptType.AVIATOR.getType());
        Object username = engine.evalString(script, env);
        Assert.assertEquals("killme2008", username); // killme2008
    }

}

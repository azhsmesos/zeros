## Zeros配置中心

愿景想通过aviator、groovy表达式的方式读取配置中心，实现脚本化

#### groovy方式获取json的某个key
- value的具体值 其中 `out_settle_param = value`, 下面json数据就是value的值
```json
{
  "settle_params":[
    {
      "seller_account_id":"1573069417",
      "account_group_key":"MERCHANT_SELLER",
      "amount":4500,
      "receiver_params_post_position":true,
      "goods_infos":[
        {
          "goods_name":"闪电购女装53",
          "goods_category":"T恤",
          "goods_unit_price":4500,
          "goods_quantity":1,
          "goods_type":"PHY",
          "goods_id":"15508048965417"
        }
      ]
    }
  ],
  "need_merge_pay":false
}
```

- 配置中心配置
```json
{
  "key": "out_settle_param",
  "script:": "json().parseText(out_settle_param)?.settle_params.collect{it.seller_account_id}"
}
```
- 语法
这样就就可以通过配置中心读取脚本配置，获取我们想要的值，目前支持aviator&groovy两种脚本
```java
String key = conf.getKey();
String script = conf.getScript();
List<Object> res = scriptValueList(key, script, jsonData, null);
res.forEach(item -> Assert.assertEquals(String.valueOf(item), "1573069417"));
```


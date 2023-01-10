## Zeros配置中心

愿景想通过aviator、groovy表达式的方式读取配置中心，实现脚本化

#### groovy方式获取json的某个key
```json
valeu = 
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

```json
script = json().parseText(out_settle_param)?.settle_params.collect{it.seller_account_id}
key = settle_params
```
- 语法
```java
String res = scriptValue(key, script, value);
// 输出值 res = 1573069417 （seller_account_id）
```


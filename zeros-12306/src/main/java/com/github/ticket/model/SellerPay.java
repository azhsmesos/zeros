package com.github.ticket.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@Getter
@Setter
public class SellerPay {

    private long id;

    private String merchantId;

    private String outOrderNo;

    private String outSubOrderNo;

    private String accountId;

    private String sellerAccountId;

    private long amount;

    private int state;

    private long createTime;
    private long completeTime;

    private String data;

    private String goodsName;

    private String goodsId;

    private long goodsUnitPrice;
}

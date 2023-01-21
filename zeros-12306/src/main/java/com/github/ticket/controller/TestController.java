package com.github.ticket.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ticket.model.CommonDataView;
import com.github.ticket.model.SellerPay;
import com.github.ticket.util.db.DataSources;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@RestController
public class TestController {

    @Resource(name = "seatDBTemplate")
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/test")
    @ResponseBody
    public CommonDataView<String> test() {
        return CommonDataView.success("test");
    }

    @GetMapping("/test/getOrderAmount")
    public CommonDataView<List<SellerPay>> queryOrder() {
        String sql = "select * from kspay_order_seller_pay_order";
        List<SellerPay> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SellerPay.class));
        return CommonDataView.success(list);
    }
}

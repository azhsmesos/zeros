package com.github.ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ticket.model.CommonDataView;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@RestController
public class TestController {


    @GetMapping("/test")
    @ResponseBody
    public CommonDataView<String> test() {
        return CommonDataView.success("test");
    }
}

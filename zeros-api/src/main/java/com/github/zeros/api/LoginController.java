package com.github.zeros.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.zeros.model.CommonDataView;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-12
 */

@RestController("/zeros/login")
public class LoginController {

    @GetMapping("/demo")
    public CommonDataView<String> demo() {
        String res = "hello world";
        return CommonDataView.success(res);
    }
}

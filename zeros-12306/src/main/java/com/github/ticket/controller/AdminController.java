package com.github.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@RestController
public class AdminController {

    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 是否登陆
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}

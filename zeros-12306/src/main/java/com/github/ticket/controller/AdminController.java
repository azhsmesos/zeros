package com.github.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.ticket.model.SysUser;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@RestController
public class AdminController {

    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 考虑是否登录状态
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            response.sendRedirect("/login.page");
        } else {
            response.sendRedirect("/admin/index.page");
        }
    }

    // 进入登录页面
    @GetMapping("/login.page")
    public String login() {
        return "login";
    }

    // 退出登录
    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        String path = "/login.page";
        response.sendRedirect(path);
    }

    // 在登录页面执行登录操作（之后也要跳转页面）
    @RequestMapping("/mockLogin.page")
    public void mockLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SysUser sysUser = SysUser.builder().id(1).username("admin").build();
        request.getSession().setAttribute("user", sysUser);
        response.sendRedirect("/admin/index.page");
    }

    // 登录成功后进入管理员首页
    @RequestMapping("/admin/index.page")
    public ModelAndView index() {
        return new ModelAndView("admin");
    }

    // 管理员首页默认加载的页面
    @GetMapping("/welcome.page")
    public String welcome() {
        return "welcome";
    }
}

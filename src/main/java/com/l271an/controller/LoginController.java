package com.l271an.controller;

import com.l271an.encryption.HashSHA256;
import com.l271an.entity.Result;
import com.l271an.entity.Users;
import com.l271an.service.UsersService;
import com.l271an.service.impl.UsersServiceImpl;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 登录（管理员可以以访客身份登录）
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("登录操作：" + username);
        UsersService user = new UsersServiceImpl();
        Map<String,Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", HashSHA256.hashSHA256(password));
        List<Users> visitor = user.userLogin(map);
        if(visitor != null && !visitor.isEmpty()){
            Result res = new Result().setCode("200").setMessage("登录成功").setData(visitor).setSuccess(true);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        Result res = new Result().setCode("401").setMessage("登录失败，请检查您的用户名或密码").setSuccess(false);
        JsonUtils.JsonUtils(resp, res);
    }
}

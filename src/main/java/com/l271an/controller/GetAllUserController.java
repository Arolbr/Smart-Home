package com.l271an.controller;

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
import java.util.List;

// 获取所有用户
@WebServlet("/intercept/getAllUser")
public class GetAllUserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UsersService user = new UsersServiceImpl();
        List<Users> users = user.getAllUsers();
        if(users != null && !users.isEmpty()){
            Result res = new Result().setCode("200").setMessage("获取成功").setData(users).setSuccess(true);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        Result res = new Result().setCode("401").setMessage("获取失败").setSuccess(false);
        JsonUtils.JsonUtils(resp, res);
    }
}

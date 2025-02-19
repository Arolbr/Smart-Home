package com.l271an.controller;

import com.l271an.encryption.HashSHA256;
import com.l271an.entity.Result;
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
import java.util.Map;

// 更新用户信息
@WebServlet("/intercept/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String username = req.getParameter("username1");
        String password = HashSHA256.hashSHA256(req.getParameter("password1"));
        String permission = req.getParameter("permission");
        UsersService user = new UsersServiceImpl();
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("password", password);
        map.put("permission", permission);
        int update = user.updateUser(map);
        if(update > 0){
            Result res = new Result().setCode("200").setData(true).setSuccess(true).setMessage("更新成功");
            JsonUtils.JsonUtils(resp,res);
        }else{
            Result res = new Result().setCode("401").setMessage("更新失败，请重试").setSuccess(false).setData(false);
            JsonUtils.JsonUtils(resp, res);
        }
    }
}

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

// 添加访客
@WebServlet("/intercept/add")
public class AddVisitorController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("addUsername");
        String password = req.getParameter("addPassword");
        String permission = req.getParameter("addPermission");
        UsersService user = new UsersServiceImpl();
        Map<String,Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", HashSHA256.hashSHA256(password));
        switch (permission) {
            case "管理员":
                map.put("permission", "Administrator");
                break;
            case "访客":
                map.put("permission", "Visitor");
                break;
            default:
                Result res = new Result().setCode("401").setMessage("添加失败，用户权限异常").setSuccess(false);
                JsonUtils.JsonUtils(resp, res);
                return;
        }
        int add = user.addUser(map);
        if(add > 0){
            Result res = new Result().setCode("200").setMessage("添加成功，添加用户权限为" + permission).setData(true).setSuccess(true);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        Result res = new Result().setCode("401").setMessage("添加失败，该用户可能已经存在").setSuccess(false).setData(false);
        JsonUtils.JsonUtils(resp, res);
    }
}

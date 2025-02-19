package com.l271an.controller;

import com.l271an.encryption.HashSHA256;
import com.l271an.entity.Result;
import com.l271an.entity.Users;
import com.l271an.service.UsersService;
import com.l271an.service.impl.UsersServiceImpl;
import com.l271an.utils.JsonUtils;
import com.sun.tools.jconsole.JConsoleContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 管理员登录
@WebServlet("/admin/login")
public class AdministratorLoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String permission = req.getParameter("permission");
        if (permission.equals("SuperAdministrator")) {
            permission = "SuperAdministrator";
        }else{
            permission = "Administrator";
        }
        UsersService user = new UsersServiceImpl();
        Map<String,Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", HashSHA256.hashSHA256(password));
        map.put("permission", permission);
        for(String key:map.keySet()){
            System.out.println("key = " + key + ", value = " + map.get(key));
        }
        List<Users> admin = user.administratorLogin(map);
        if(admin != null && !admin.isEmpty()){
            Result res = new Result().setCode("200").setMessage("登录成功").setData(admin).setSuccess(true);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        Result res = new Result().setCode("401").setMessage("登录失败，请检查您的用户名或密码以及权限").setSuccess(false);
        JsonUtils.JsonUtils(resp, res);
    }
}

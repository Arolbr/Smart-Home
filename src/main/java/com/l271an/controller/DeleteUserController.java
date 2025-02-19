package com.l271an.controller;

import com.l271an.entity.Result;
import com.l271an.service.UsersService;
import com.l271an.service.impl.UsersServiceImpl;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 删除用户
@WebServlet("/intercept/delete")
public class DeleteUserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        UsersService user = new UsersServiceImpl();
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        int delete = user.deleteUser(map);
        if(delete == 1){
            Result res = new Result().setCode("200").setData(true).setSuccess(true).setMessage("删除成功");
            JsonUtils.JsonUtils(resp,res);
        }else {
            Result res = new Result().setCode("401").setSuccess(false).setMessage("删除失败").setData(false);
            JsonUtils.JsonUtils(resp,res);
        }
    }
}

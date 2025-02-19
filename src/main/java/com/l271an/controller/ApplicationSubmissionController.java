package com.l271an.controller;

import com.l271an.entity.Result;
import com.l271an.service.ApplicationService;
import com.l271an.service.impl.ApplicationServiceImpl;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 提交申请
@WebServlet("/intercept/submit")
public class ApplicationSubmissionController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String content = req.getParameter("content");
        String time = req.getParameter("time");
        String userId = req.getParameter("userId");

        // 将申请内容添加到数据库
        ApplicationService app = new ApplicationServiceImpl();
        Map map = new HashMap();
        map.put("user", userId);
        map.put("time", time);
        map.put("content", content);
        int addResult = app.addApplication(map);
        if (addResult > 0){
            Result res = new Result().setCode("200").setMessage("提交成功").setData(addResult).setSuccess(true);
            JsonUtils.JsonUtils(resp,res);
            return;
        }
        Result res = new Result().setCode("401").setMessage("提交申请失败").setSuccess(false);
        JsonUtils.JsonUtils(resp,res);
    }
}

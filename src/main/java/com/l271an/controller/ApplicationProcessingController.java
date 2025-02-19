package com.l271an.controller;

import com.l271an.entity.Applications;
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
import java.util.List;
import java.util.Map;

// 审批申请
@WebServlet("/intercept/apply")
public class ApplicationProcessingController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String applicationContent = req.getParameter("applicationContent");
        String applicationStatus = req.getParameter("applicationStatus");
        String applicationId = req.getParameter("applicationId");
        String applicationOperate = req.getParameter("applicationOperate");
        ApplicationService app = new ApplicationServiceImpl();
        Map map = new HashMap();
        // 同意申请
        if(applicationOperate.equals("agree")){
            map.put("id", applicationId);
            map.put("status", 1);
            int updateResult = app.updateApplicationStatus(map);
            Result res = new Result().setSuccess(true).setMessage("已同意").setCode("200").setData(updateResult);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        // 拒绝申请
        if (applicationOperate.equals("refuse")){
            map.put("id", applicationId);
            map.put("status", -1);
            int updateResult = app.updateApplicationStatus(map);
            Result res = new Result().setData(updateResult).setCode("200").setMessage("已拒绝").setSuccess(true);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        // 删除申请
        if(applicationOperate.equals("delete")){
            map.put("id", applicationId);
            int deleteResult = app.deleteApplication(map);
            Result res = new Result().setCode("200").setMessage("已删除").setSuccess(true).setData(deleteResult);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        // 获取所有申请
        if (applicationOperate.equals("selectAll")){
            List<Applications> applications = app.getApplications();
            Result res = new Result().setCode("200").setMessage("获取申请列表成功").setSuccess(true).setData(applications);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        // 根据用户获取申请
        if(applicationOperate.equals("selectById")){
            map.put("user", username);
            List<Applications> applications = app.getApplicationByUserId(map);
            Result res = new Result().setCode("200").setMessage("获取申请列表成功").setSuccess(true).setData(applications);
            JsonUtils.JsonUtils(resp, res);
            return;
        }

        Result res = new Result().setSuccess(false).setMessage("出现未知错误").setCode("401");
        JsonUtils.JsonUtils(resp,res);
    }
}
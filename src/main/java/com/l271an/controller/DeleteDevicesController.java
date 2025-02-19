package com.l271an.controller;

import com.l271an.entity.Result;
import com.l271an.service.DeviceService;
import com.l271an.service.impl.DeviceServiceImpl;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/intercept/deleteDevice")
public class DeleteDevicesController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DeviceService del = new DeviceServiceImpl();
        String id = req.getParameter("id");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        int delDev = del.deleteDevice(map);
        if(delDev > 0){
            JsonUtils.JsonUtils(resp, new Result().setCode("200").setSuccess(true).setMessage("删除设备").setData(true));
            return;
        }
        JsonUtils.JsonUtils(resp, new Result().setCode("401").setSuccess(true).setMessage("删除设备").setData(false));
    }
}

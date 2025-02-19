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

@WebServlet("/intercept/addDevice")
public class AddDevicesController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DeviceService device = new DeviceServiceImpl();
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String status = req.getParameter("status");
        String rooms = req.getParameter("rooms");
        String ip = req.getParameter("ip");
        String images = req.getParameter("images");

        if(status.equals("关闭")){
            status = "0";
        }
        else
        {
            status = "-1";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", description);
        map.put("type", type);
        map.put("status", status);
        map.put("rooms", rooms);
        map.put("ip", ip);
        map.put("images", images);

        int add = device.addDevice(map);
        if(add > 0){
            JsonUtils.JsonUtils(resp, new Result().setCode("200").setSuccess(true).setData(true).setMessage("添加设备"));
            return;
        }
        JsonUtils.JsonUtils(resp, new Result().setCode("401").setSuccess(true).setMessage("添加设备").setData(false));
    }
}

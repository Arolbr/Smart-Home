package com.l271an.controller;

import com.l271an.entity.Devices;
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
import java.util.List;
import java.util.Map;

// 获取全部设备
@WebServlet("/intercept/getAllDevices")
public class GetAllDevicesController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        DeviceService device = new DeviceServiceImpl();
        List<Devices> devices = device.getAllDevices();
        Result res = new Result().setMessage("所有设备信息").setCode("200").setSuccess(true).setData(devices);
        JsonUtils.JsonUtils(resp,res);
    }
}

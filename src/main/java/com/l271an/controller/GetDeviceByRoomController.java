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

@WebServlet("/intercept/getDeviceByRoom")
public class GetDeviceByRoomController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String roomName = req.getParameter("room");
        // 获取该房间下的所有设备
        DeviceService device = new DeviceServiceImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("rooms", roomName);
        List<Devices> dev = device.getDeviceByRoom(map);
        if(dev != null && !dev.isEmpty()) {
            Result res = new Result().setCode("200").setMessage("该房间内的设备").setSuccess(true).setData(dev);
            JsonUtils.JsonUtils(resp, res);
            return;
        }
        Result res = new Result().setCode("200").setMessage("该房间内没有任何设备").setSuccess(false);
        JsonUtils.JsonUtils(resp, res);
    }
}

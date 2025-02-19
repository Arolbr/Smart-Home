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

@WebServlet("/intercept/openOrCloseDevice")
public class OpenOrCloseDeviceController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 获取参数
        String id = req.getParameter("id");
        String ip = null;

        // 参数校验
        if (id == null || id.isEmpty()) {
            Result errorResult = new Result().setCode("400").setMessage("参数id不能为空").setData(false);
            JsonUtils.JsonUtils(resp, errorResult);
            return;
        }

        DeviceService dev = new DeviceServiceImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        // 检查设备状态
        boolean isRunning = dev.inspectDeviceStatusById(map);

        int res;

        // ip操作
        if(req.getParameter("ip") != null && !req.getParameter("ip").isEmpty()){
            ip = req.getParameter("ip");
            map.put("ip", ip);
            // 关闭设备
            if(isRunning){
                // 关闭设备
                map.put("status", 0);
                res = dev.stopDeviceByIp(map);
                if (res > 0) {
                    JsonUtils.JsonUtils(resp, new Result().setCode("200").setMessage("设备[" + ip + "]已关闭").setData(false).setSuccess(true));
                } else {
                    JsonUtils.JsonUtils(resp, new Result().setCode("500").setMessage("设备[" + ip + "]关闭失败").setData(false).setSuccess(false));
                }
            }else{
                // 运行设备
                map.put("status", 1);
                res = dev.runningDeviceByIp(map);
                if (res > 0) {
                    JsonUtils.JsonUtils(resp, new Result().setCode("200").setMessage("设备[" + ip + "]已开启").setData(true).setSuccess(true));
                } else {
                    JsonUtils.JsonUtils(resp, new Result().setCode("500").setMessage("设备[" + ip + "]开启失败").setData(false).setSuccess(false));
                }
            }
            return;
        }

        // id操作
        if (isRunning) {
            // 关闭设备
            map.put("status", 0);
            res = dev.stopDeviceById(map);
            if (res > 0) {
                JsonUtils.JsonUtils(resp, new Result().setCode("200").setMessage("设备[" + id + "]已关闭").setData(false).setSuccess(true));
            } else {
                JsonUtils.JsonUtils(resp, new Result().setCode("500").setMessage("设备[" + id + "]关闭失败").setData(false).setSuccess(false));
            }
        } else {
            // 运行设备
            map.put("status", 1);
            res = dev.runningDeviceById(map);
            if (res > 0) {
                JsonUtils.JsonUtils(resp, new Result().setCode("200").setMessage("设备[" + id + "]已开启").setData(true).setSuccess(true));
            } else {
                JsonUtils.JsonUtils(resp, new Result().setCode("500").setMessage("设备[" + id + "]开启失败").setData(false).setSuccess(false));
            }
        }
    }
}

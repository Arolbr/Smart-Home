package com.l271an.controller;

import com.l271an.entity.Data;
import com.l271an.entity.Result;
import com.l271an.mapper.DataMapper;
import com.l271an.service.DataService;
import com.l271an.service.impl.DataServiceImpl;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 获取所有预设数据
@WebServlet("/intercept/getAllData")
public class GetDataController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        DataService data = new DataServiceImpl();
        List<Data> r = data.getData();
        if(r != null && !r.isEmpty()){
            JsonUtils.JsonUtils(resp, new Result().setData(r).setCode("200").setMessage("获取所有预设数据").setSuccess(true));
            return;
        }
        JsonUtils.JsonUtils(resp, new Result().setData(null).setSuccess(true).setMessage("获取所有预设数据失败").setCode("401"));
    }
}

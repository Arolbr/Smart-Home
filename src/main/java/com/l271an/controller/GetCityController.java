package com.l271an.controller;

import com.l271an.entity.Result;
import com.l271an.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/intercept/getCity")
public class GetCityController extends HttpServlet {
    private static final String API_KEY = "a61fedfdc0f0feef8f2d86a28821e035";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 接收前端传入的经纬度
        double j = Double.parseDouble(req.getParameter("longitude"));
        double w = Double.parseDouble(req.getParameter("latitude"));

        try {
            String jsonResponse = getString(j, w);
            JsonUtils.JsonUtils(resp, new Result().setMessage("获取位置").setCode("200").setData(jsonResponse).setSuccess(true));

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    private static String getString(double j, double w) throws IOException {
        BufferedReader reader = getReader(j, w);
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        // 返回城市数据
        return response.toString();
    }

    private static BufferedReader getReader(double j, double w) throws IOException {
        String urlString = String.format("https://restapi.amap.com/v3/geocode/regeo?key=%s&location=%f,%f&extensions=all",
                API_KEY, j, w);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);  // 设置连接超时
        connection.setReadTimeout(5000);     // 设置读取超时

        // 获取响应内容
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}

package com.l271an.utils;

import com.google.gson.Gson;
import com.l271an.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JsonUtils {
    public static void JsonUtils(HttpServletResponse resp, Result result){
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.println(new Gson().toJson(result));
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }
}

package com.l271an.filter;

import com.l271an.encryption.HashSHA256;
import com.l271an.entity.Users;
import com.l271an.service.UsersService;
import com.l271an.service.impl.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = "/intercept/*")
public class SecondFilter implements Filter {

    private UsersService users;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         users = new UsersServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = request.getParameter("token");

        // 空值检查
        if (username == null || password == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "帐号或密码不能为空");
            return;
        }

        // 匹配用户
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);
            List<Users> user = users.userLogin(params);
            if(user == null || user.isEmpty()){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "帐号或密码错误");
                return;
            }
            String tokenInspect = user.getFirst().getName() + user.getFirst().getPermission() + user.getFirst().getPassword();
            if(!tokenInspect.equals(token)){
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "无效Token令牌");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

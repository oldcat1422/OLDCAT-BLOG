package com.jh.oldcat.filter;

import com.jh.oldcat.service.UserService;
import com.jh.oldcat.utils.JWTUtils;
import com.jh.oldcat.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@Slf4j
@WebFilter(urlPatterns = "/*")
@Component
public class LoginFilter implements Filter {




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }


@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // ########################## 关键：统一设置CORS响应头 ##########################
    // 允许的源（根据实际前端地址修改，如 http://localhost:5173）
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
    // 允许的请求方法
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    // 允许的请求头（需包含前端实际传递的头，如 Authorization）
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    // 允许携带凭证（如cookie，根据需求设置）
    response.setHeader("Access-Control-Allow-Credentials", "true");
    // 预检请求的缓存时间（减少OPTIONS请求次数）
    response.setHeader("Access-Control-Max-Age", "3600");

    // 处理预检请求（OPTIONS请求）：直接返回成功
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        response.setStatus(HttpServletResponse.SC_OK);
        return;
    }

    // 排除登录接口和公开接口
    String requestURI = request.getRequestURI();
    if (requestURI.equals("/user/login") || requestURI.startsWith("/public/")) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    // 获取并验证token
    String token = request.getHeader("Authorization");

    Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);

    if (stringObjectMap == null) {
        // ########################## 错误响应也已包含CORS头 ##########################
        response.setContentType("application/json;charset=UTF-8"); // 指定JSON格式
        response.getWriter().write("{\"code\":401,\"message\":\"Token无效或已过期\"}");
        return;
    }
    
    filterChain.doFilter(servletRequest, servletResponse);
}

    @Override
    public void destroy() {
        // 销毁操作
    }

}
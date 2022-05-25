package com.example.backend.config;

import com.example.backend.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             @SuppressWarnings("NullableProblems") HttpServletResponse response,
                             @SuppressWarnings("NullableProblems") Object handler) {
        return TokenUtil.verifyToken(request.getHeader("Authorization").substring(7));
    }

}

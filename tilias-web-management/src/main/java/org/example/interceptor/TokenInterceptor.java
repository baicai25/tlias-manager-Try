package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //获取到请求路径
//        String requestURI = request.getRequestURI();
//
//        //判断是否是登录请求,看路径中有没有login,是就放行
//        if (requestURI.contains("/login")) {
//            log.info("登录操作直接放行");
//            return true;
//        }

        //获取请求头中的token
        String token = request.getHeader("token");

        //判断token,不存在,证明没有登录,用户返回错误信息401状态码
        if (token == null || token.isEmpty()) {
            log.info("令牌为空,用户未登录,响应401");
            response.setStatus(401);
            return false;
        }

        //存在就校验令牌,校验失败就返回错误信息401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法,用户未登录,响应401");
            response.setStatus(401);
            return false;
        }

        //校验通过就放行
        log.info("令牌合法,放行");
        return true;
    }
}


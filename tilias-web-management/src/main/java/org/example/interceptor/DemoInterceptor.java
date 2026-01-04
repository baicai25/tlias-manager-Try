package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {
    //在目标资源运行前运行 -返回值 true放行,false不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("PreHandle 方法1...");
        return true;
        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    //在目标资源运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("PostHandle 方法2...");
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //在视图渲染完成后运行,现在前后端分离,没有渲染了,总之最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterHandle 方法3...");
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

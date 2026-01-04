package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
public class DemoFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DemoFilter.class);

    @Override//初始化方法.web服务器启动的时候执行,且只执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init  初始化方法");
        Filter.super.init(filterConfig);
    }

    @Override//拦截到请求后执行,会执行多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求00000000000000000");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("拦截到了请求1111111111111111");
    }

    @Override//销毁方法.web服务器关闭的时候执行,且只执行一次
    public void destroy() {
        log.info("destroy  的销毁方法.....");
        Filter.super.destroy();
    }
}

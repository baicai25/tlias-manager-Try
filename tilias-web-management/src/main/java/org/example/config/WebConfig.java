package org.example.config;

import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//底层封装了Conponennt注解
public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
////    private DemoInterceptor demoInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        WebMvcConfigurer.super.addInterceptors(registry);
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")//拦截所有请求
//                .excludePathPatterns("/login");
//
//    }
}

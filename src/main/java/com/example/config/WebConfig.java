//package com.example.config;
//
//import com.example.interceptor.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * ClassName: webConfig
// * Package: com.example.config
// * Description:
// *
// * @Author yzz
// * @Create 2023/11/15 9:31
// * @Version 1.0
// */
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//    }
//}

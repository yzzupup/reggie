package com.example.config;

import com.example.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebSerMvcConfig
 * Package: com.example.config
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 14:30
 * @Version 1.0
 */
@Configuration
@Slf4j
public class WebSerMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("建立映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//    }

}

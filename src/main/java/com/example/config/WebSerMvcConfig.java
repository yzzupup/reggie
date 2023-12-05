package com.example.config;

import com.example.common.JacksonObjectMapper;
import com.example.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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


    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, converter);
        super.extendMessageConverters(converters);
    }
}

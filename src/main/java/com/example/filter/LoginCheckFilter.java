package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.common.BaseContext;
import com.example.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ClassName: LoginCheckFilter
 * Package: com.example.filter
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/14 15:28
 * @Version 1.0
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/*"})
@Slf4j
public class LoginCheckFilter implements Filter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Value("${filter.run}")
    private int filterRun;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        if(filterRun == 0 || check(urls, request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }

        Object employee = request.getSession().getAttribute("employee");
        if(employee == null){
            log.info("{} 拦截请求 {}", LocalDateTime.now(), request.getRequestURI());
            response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
            return;
        }

        BaseContext.setCurrentId((Long) employee);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


    public boolean check(String[] urls, String requestUrl){
        for (String url : urls) {
            if(PATH_MATCHER.match(url, requestUrl))
                return true;
        }
        return false;
    }
}

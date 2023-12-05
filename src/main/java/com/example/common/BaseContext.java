package com.example.common;

import org.springframework.stereotype.Component;

/**
 * ClassName: BaseContext
 * Package: com.example.common
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/29 9:28
 * @Version 1.0
 */
@Component
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}

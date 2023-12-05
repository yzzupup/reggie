package com.example.exception;

import org.springframework.stereotype.Component;

/**
 * ClassName: CustomException
 * Package: com.example.exception
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/29 16:16
 * @Version 1.0
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}

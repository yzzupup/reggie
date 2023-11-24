package com.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * ClassName: ControllerExceptionHandler
 * Package: com.example.common
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/17 11:01
 * @Version 1.0
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler({Exception.class})
    public R<String> exceptionHandler(Exception ex){

        log.error(String.format("%s 发生异常 %s", LocalDateTime.now(), ex.getMessage()));

        return R.error("出现未知错误");
    }
}

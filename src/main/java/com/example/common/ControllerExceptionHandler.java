package com.example.common;

import com.example.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${package.name}")
    private String packageName;
    @ExceptionHandler({Exception.class})
    public R<String> exceptionHandler(Exception ex){

//        // 获取异常堆栈信息
//        StackTraceElement[] stackTrace = ex.getStackTrace();
//
//        // 查找第一个指向您自己代码的元素
//        StackTraceElement firstCustomElement = findFirstCustomElement(stackTrace);
//
//        // 获取异常发生的类名
//        String controllerName = firstCustomElement.getClassName();
//
//        // 获取异常发生的代码行号
//        int lineNumber = firstCustomElement.getLineNumber();
//
//        log.error(String.format("\n ERROR! %s 发生异常" +
//                " \n 异常原因: %s" +
//                "\n 异常类为: %s" +
//                "\n 异常位置: %s",
//                LocalDateTime.now(), ex.getMessage(), controllerName, lineNumber));

        log.error(String.format("\n ERROR! %s 发生异常" +
                " \n 异常原因: %s",
                LocalDateTime.now(), ex.getMessage()));

        ex.printStackTrace();

        String msg = ex.getMessage();
        if(ex instanceof CustomException)
            return R.error(msg);

        return R.error("出现未知错误");
    }

    private StackTraceElement findFirstCustomElement(StackTraceElement[] stackTrace) {
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className.startsWith("com.example")) {
                return element;
            }
        }

        return stackTrace[0];
    }
}

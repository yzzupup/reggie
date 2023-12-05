package com.example.aspect;

import com.example.common.R;
import com.example.entity.Category;
import com.example.entity.ControllerRule;
import com.example.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * ClassName: ControllerAspect
 * Package: com.example.aspect
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 15:19
 * @Version 1.0
 */

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public HashMap<String, ControllerRule> localRule = new HashMap<>();
    @Around("execution(* com.example.controller.*.insert*(..))" +
            "|| execution(* com.example.controller.*.update*(..))" +
            "|| execution(* com.example.controller.*.delete*(..))")
    public Object beforeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        if(localRule.containsKey(name)){

            Object[] args = joinPoint.getArgs();

            if(methodName.contains("delete")){
                if(checkThisExist(name, Long.parseLong(args[0].toString())) == false)
                    return R.error(localRule.get(name).getFieldRes());
            }
            else{

                String value = "";
                Long id = 0L;

                for (Object arg: args) {

                    if(arg instanceof Employee) {
                        Employee employee = (Employee) arg;
                        id = employee.getId();
                        value = employee.getUsername();
                        break;
                    }
                    else if(arg instanceof Category) {
                        Category category = (Category) arg;
                        id = category.getId();
                        value = category.getName();
                        break;
                    }
                }

                if(!"".equals(value)){

                    if(methodName.contains("update") && checkThisExist(name, id) == false)
                        return R.error(localRule.get(name).getFieldRes());

                    if(checkTargetExist(name, value) == false)
                        return R.error(localRule.get(name).getObjRes());

                }
            }
        }

        Object res = joinPoint.proceed();
        return res;


//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//
//        log.info("接口调用前记录日志...");
//        log.info("方法名: " + method.getName());
//        log.info("参数: " + Arrays.toString(args));
//        log.info("调用时间: " + LocalDateTime.now());
    }


    private boolean checkThisExist(String name, Long id){
        String sql = String.format(localRule.get(name).getIdSql(), id.toString());
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        if(count == 0)
            return false;
        return true;
    }

    private boolean checkTargetExist(String name, String value){

        String sql = String.format(localRule.get(name).getNameSql(), value);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        if(count > 0)
            return false;
        return true;
    }
}




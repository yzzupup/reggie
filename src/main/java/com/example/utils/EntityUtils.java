package com.example.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * ClassName: EntityUtils
 * Package: com.example.utils
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 10:24
 * @Version 1.0
 */
@Slf4j
public class EntityUtils {
    public static <T> void setDefault(T obj){

        try {
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(obj);
                if(o == null){
                    Class<?> type = field.getType();
                    if(type == Integer.class)
                        field.set(obj, 0);
                    else if(type == String.class)
                        field.set(obj, "");
                    else if(type == LocalDateTime.class)
                        field.set(obj, LocalDateTime.now());
                }
            }
        }
        catch (Exception exception){
            log.error(exception.getMessage());
        }
        return;
    }
}

package com.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

/**
 * ClassName: YzzTool
 * Package: com.example.utils
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/14 15:00
 * @Version 1.0
 */
@Slf4j
public class YzzTool {
    public static <T> void copyAttributes(T from, T to) {
        try {
            Field[] fields = to.getClass().getDeclaredFields();
            for (Field field : fields) {

                int value = field.getModifiers();
                if(Modifier.isFinal(value) || Modifier.isStatic(value))
                    continue;

                field.setAccessible(true);
                field.set(to, field.get(from));
            }
        }
        catch (Exception ex){
            log.error(String.format("%s copyAttributes error: %s", LocalDateTime.now(), ex.getMessage()));
        }
        finally {
            return;
        }
    }
}

package com.example.service;

import java.util.HashMap;

/**
 * ClassName: DishService
 * Package: com.example.service
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 9:54
 * @Version 1.0
 */
public interface DishService {
    HashMap<String, Object> getByPage(Integer page, Integer pageSize, String name);
}

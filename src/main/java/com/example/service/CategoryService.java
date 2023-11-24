package com.example.service;

import com.example.entity.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * ClassName: CategoryService
 * Package: com.example.service
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 10:47
 * @Version 1.0
 */
public interface CategoryService {
    HashMap<String, Object> getByPage(Integer page, Integer pageSize);

    Integer insertDishOrSetmealCategory(HttpServletRequest request, Category category);
}

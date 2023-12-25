package com.example.service;

import com.example.dto.DishDto;
import com.example.entity.Dish;

import java.util.HashMap;
import java.util.List;

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

    int insertNewDish(DishDto dishDto);

    DishDto getById(Long id);

    int updateById(DishDto dishDto);

    List<Dish> getByCategory(Long id);
}

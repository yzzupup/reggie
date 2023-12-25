package com.example.service;

import com.example.dto.SetmealDto;

import java.util.HashMap;

/**
 * ClassName: SetMealService
 * Package: com.example.service
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 21:31
 * @Version 1.0
 */
public interface SetmealService {
    int insertNewSetmeal(SetmealDto setmealDto);

    HashMap<String, Object> getByPage(Integer page, Integer pageSize);

    Integer deleteById(Long[] ids);
}

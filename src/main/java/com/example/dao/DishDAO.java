package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: DishDAO
 * Package: com.example.dao
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 9:55
 * @Version 1.0
 */
@Mapper
public interface DishDAO extends BaseMapper<Dish> {
    List<Dish> getAll(String name);
}

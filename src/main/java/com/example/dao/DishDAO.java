package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Dish;
import com.example.entity.DishShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from dish_dto order by update_time desc ")
    List<DishShow> getDishShow(String name);


    @Select("select * from dish_dto where id = #{id}")
    DishShow selectDishShowById(Long id);

    @Select("select * from dish where category_id = #{id}")
    List<Dish> getByCategory(Long id);
}

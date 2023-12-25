package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: CategoryDAO
 * Package: com.example.dao
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 10:48
 * @Version 1.0
 */
@Mapper
public interface CategoryDAO extends BaseMapper<Category> {

    @Select("select * from category")
    List<Category> getAll();

    @Select("select count(name) from dish where category_id = #{ids}")
    Integer selectDishById(String ids);

    @Select("select count(name) from setmeal where category_id = #{ids}")
    Integer selectSetmealById(String ids);

    @Select("select * from category where type = #{type}")
    List<Category> getByType(Integer type);
}

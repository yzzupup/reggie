package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: DishFlavorDAO
 * Package: com.example.dao
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 14:17
 * @Version 1.0
 */
@Mapper
public interface DishFlavorDAO extends BaseMapper<DishFlavor> {
    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> selectByCategoryId(Long id);

    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);
}

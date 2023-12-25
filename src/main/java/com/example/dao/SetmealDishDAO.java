package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Setmeal;
import com.example.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: SetmealDishDAO
 * Package: com.example.dao
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 21:37
 * @Version 1.0
 */
@Mapper
public interface SetmealDishDAO extends BaseMapper<SetmealDish> {


    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);
}

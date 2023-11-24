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
}

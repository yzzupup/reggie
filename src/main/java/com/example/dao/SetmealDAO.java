package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.SetmealDto;
import com.example.entity.Setmeal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: SetmealDAO
 * Package: com.example.dao
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 21:35
 * @Version 1.0
 */
@Mapper
public interface SetmealDAO extends BaseMapper<Setmeal> {

    @Select("select * from setmeal_dto where is_deleted = 0 order by update_time desc")
    List<SetmealDto> getAll();

    @Delete("delete from setmeal where id = #{id} and status = 0")
    Integer deleteById(Long id);

    @Select("select status, id from setmeal where is_deleted = 0")
    List<Setmeal> selectStatus();
}

package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: EmployeeMapper
 * Package: com.example.Mapper
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 15:25
 * @Version 1.0
 */
@Mapper
public interface EmployeeDAO extends BaseMapper<Employee> {

    @Select("select * from employee where username = #{username}")
    Employee selectByUsername(String username);

    @Select("select * from employee")
    List<Employee> getAll();
}

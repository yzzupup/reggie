package com.example.service;

import com.example.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * ClassName: EmployeeService
 * Package: com.example.service
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 15:24
 * @Version 1.0
 */
public interface EmployeeService {
    Integer login(Employee employee);

    Integer insert(HttpServletRequest request, Employee employee);

    HashMap<String, Object> getByPage(Integer page, Integer row);
}

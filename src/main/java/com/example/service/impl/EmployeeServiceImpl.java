package com.example.service.impl;

import com.example.dao.EmployeeDAO;
import com.example.common.LoginEnum;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.example.utils.YzzTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 15:25
 * @Version 1.0
 */
@Controller
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Integer login(Employee employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        Employee toEmployee = employeeDAO.selectByUsername(employee.getUsername());
        if(toEmployee == null)
            return LoginEnum.NO_USER;

        if(!password.equals(toEmployee.getPassword()))
            return LoginEnum.PASSWORD_ERROR;

        if(toEmployee.getStatus().equals(0))
            return LoginEnum.BANNED;

        YzzTool.copyAttributes(toEmployee, employee);
        return LoginEnum.LOGIN_SUCCESS;
    }

    @Override
    public void insert(HttpServletRequest request, Employee employee) {

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long fromId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(fromId);
        employee.setUpdateUser(fromId);

        employeeDAO.insert(employee);

        return;

    }
}

package com.example.service.impl;

import com.example.dao.EmployeeDAO;
import com.example.common.ResEnum;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.example.utils.EntityUtils;
import com.example.utils.YzzTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 15:25
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Integer login(Employee employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        Employee toEmployee = employeeDAO.selectByUsername(employee.getUsername());
        if(toEmployee == null)
            return ResEnum.NO_USER;

        if(!password.equals(toEmployee.getPassword()))
            return ResEnum.PASSWORD_ERROR;

        if(toEmployee.getStatus().equals(0))
            return ResEnum.BANNED;

        YzzTool.copyAttributes(toEmployee, employee);
        return ResEnum.SUCCESS;
    }

    @Override
    public Integer insert(HttpServletRequest request, Employee employee) {

        EntityUtils.setDefault(employee);

        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long fromId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(fromId);
        employee.setUpdateUser(fromId);

        employeeDAO.insert(employee);

        return ResEnum.SUCCESS;

    }

    @Override
    public HashMap<String, Object> getByPage(Integer page, Integer row, String name) {

        PageHelper.startPage(page, row);
        PageInfo info = new PageInfo(employeeDAO.getAll(name));

        HashMap<String, Object> res = new HashMap<>();
        res.put("total", info.getTotal());
        res.put("records", info.getList());

        return res;
    }

    @Override
    public Integer updateStateFieldById(HttpServletRequest request, Employee employee) {

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));

        int res = employeeDAO.updateById(employee);

        if(res > 0)
            return ResEnum.SUCCESS;
        return ResEnum.UNKNOWN_ERROR;
    }

    @Override
    public Employee getById(Long id) {

        return employeeDAO.selectById(id);
    }

}

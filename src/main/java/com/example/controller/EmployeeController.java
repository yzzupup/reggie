package com.example.controller;

import com.example.common.LoginEnum;
import com.example.common.R;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: EmployeeController
 * Package: com.example.controller
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/13 15:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        Integer res = employeeService.login(employee);

        switch (res){
            case LoginEnum.NO_USER:
                return R.error("用户不存在");

            case LoginEnum.PASSWORD_ERROR:
                return R.error("密码错误");

            case LoginEnum.BANNED:
                return R.error("用户已被禁用");

            case LoginEnum.LOGIN_SUCCESS:
            default:
                request.getSession().setAttribute("employee", employee.getId());
                return R.success(employee);
        }
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    @PostMapping
    public R<String> insert(HttpServletRequest request, @RequestBody Employee employee){

        employeeService.insert(request, employee);

        return R.success("新增成功");
    }

}

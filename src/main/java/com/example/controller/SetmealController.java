package com.example.controller;

import com.example.aspect.ControllerAspect;
import com.example.entity.ControllerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * ClassName: SetmealController
 * Package: com.example.controller
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 10:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private ControllerAspect controllerAspect;

    @PostConstruct
    void init(){
        ControllerRule controllerRule = new ControllerRule();
        controllerRule.setNameSql("select count(*) from setmeal where name = '%s'");
        controllerRule.setIdSql("select count(*) from setmeal where id = '%s'");
        controllerRule.setObjRes("套餐已存在");
        controllerRule.setFieldRes("套餐不存在");

        controllerAspect
                .localRule
                .put(this.getClass().getName(), controllerRule);
    }

}

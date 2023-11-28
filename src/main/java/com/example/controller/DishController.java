package com.example.controller;

import com.example.aspect.ControllerAspect;
import com.example.common.R;
import com.example.entity.ControllerRule;
import com.example.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * ClassName: DishController
 * Package: com.example.controller
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 9:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private ControllerAspect controllerAspect;

    @Autowired
    private DishService dishService;

    @PostConstruct
    public void init(){

        ControllerRule controllerRule = new ControllerRule();
        controllerRule.setObjRes("菜品已存在");
        controllerRule.setFieldRes("菜品不存在");
        controllerRule.setNameSql("select count(*) from dish where name = '%s'");
        controllerRule.setIdSql("select count(*) from dish where id = '%s'");


        controllerAspect
                .localRule
                .put(this.getClass().getName(), controllerRule);
    }

    @GetMapping("/page")
    public R<HashMap<String, Object>> getByPage(Integer page, Integer pageSize, String name){

        HashMap<String, Object> res = dishService.getByPage(page, pageSize, name);
        return R.success(res);

    }



}

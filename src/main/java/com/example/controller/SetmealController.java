package com.example.controller;

import com.example.aspect.ControllerAspect;
import com.example.common.R;
import com.example.common.ResEnum;
import com.example.dto.SetmealDto;
import com.example.entity.ControllerRule;
import com.example.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SetmealService setmealService;

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


    @GetMapping("/page")
    public R<HashMap<String, Object>> getByPage(Integer page, Integer pageSize){
        return R.success(setmealService.getByPage(page, pageSize));
    }

    @PostMapping
    public R<String> insertNewSetmeal(@RequestBody SetmealDto setmealDto){

        setmealService.insertNewSetmeal(setmealDto);
        return R.success("新增成功");
    }


    @DeleteMapping
    public R<String> deleteById(@RequestParam Long[] ids){
        setmealService.deleteById(ids);

        return R.success("删除成功!");
    }

}

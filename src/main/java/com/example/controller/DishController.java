package com.example.controller;

import com.example.aspect.ControllerAspect;
import com.example.common.R;
import com.example.dto.DishDto;
import com.example.entity.Category;
import com.example.entity.ControllerRule;
import com.example.entity.Dish;
import com.example.entity.DishShow;
import com.example.service.CategoryService;
import com.example.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private CategoryService categoryService;

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

    @PostMapping
    public R<String> insertNewDish(@RequestBody DishDto dishDto){

        dishService.insertNewDish(dishDto);

        return R.success("新增成功");
    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id){

        DishDto dishDto = dishService.getById(id);

        return R.success(dishDto);
    }

    @PutMapping
    public R<String> updateById(@RequestBody DishDto dishDto){

        dishService.updateById(dishDto);

        return R.success("修改成功");
    }

    @GetMapping("list")
    public R<List<Dish>> getByCategory(Long categoryId){

        List<Dish> res = dishService.getByCategory(categoryId);
        return R.success(res);

    }

}

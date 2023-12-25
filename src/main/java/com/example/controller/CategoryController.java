package com.example.controller;

import com.example.aspect.ControllerAspect;
import com.example.entity.Category;
import com.example.entity.ControllerRule;
import com.example.service.CategoryService;
import com.fasterxml.jackson.core.ObjectCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.common.R;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.example.controller
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 10:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ControllerAspect controllerAspect;

    @PostConstruct
    public void init(){
        ControllerRule controllerRule = new ControllerRule();
        controllerRule.setObjRes("分类已存在");
        controllerRule.setFieldRes("分类不存在");
        controllerRule.setNameSql("select count(*) from Category where name = '%s'");
        controllerRule.setIdSql("select count(*) from Category where id = '%s'");


        controllerAspect
                .localRule
                .put(this.getClass().getName(), controllerRule);
    }
    @GetMapping("/page")
    public R<HashMap<String, Object>> getByPage(Integer page, Integer pageSize){

        HashMap<String, Object> res = categoryService.getByPage(page, pageSize);

        return R.success(res);
    }

    @PostMapping
    public R<String> insertDishOrSetmealCategory(HttpServletRequest request, @RequestBody Category category){

        categoryService.insertDishOrSetmealCategory(request, category);

        return R.success("新增成功");
    }


    @DeleteMapping
    public R<String> deletedFieldsById(String ids){
        categoryService.deleteFieldsById(ids);

        return R.success("删除成功");
    }

    @PutMapping
    public R<String> updateById(@RequestBody Category category){

        categoryService.updateById(category);

        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        List<Category> categoryList = categoryService.getCategoryList(category.getType());
        return R.success(categoryList);
    }

}

package com.example.service.impl;

import com.example.common.ResEnum;
import com.example.dao.CategoryDAO;
import com.example.dao.DishDAO;
import com.example.entity.Category;
import com.example.exception.CustomException;
import com.example.service.CategoryService;
import com.example.utils.EntityUtils;
import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/22 10:47
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public HashMap<String, Object> getByPage(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        PageInfo info = new PageInfo(categoryDAO.getAll());

        HashMap<String, Object> res = new HashMap<>();
        res.put("total", info.getTotal());
        res.put("records", info.getList());

        return res;
    }

    @Override
    public Integer insertDishOrSetmealCategory(HttpServletRequest request, Category category) {

        Long employee = (Long) request.getSession().getAttribute("employee");
        category.setCreateUser(employee);
        category.setUpdateUser(employee);

        EntityUtils.setDefault(category);
        categoryDAO.insert(category);

        return ResEnum.SUCCESS;
    }

    @Override
    public Integer deleteFieldsById(String ids) {

        if(categoryDAO.selectDishById(ids) > 0)
            throw new CustomException("该分类在菜品中已存在");

        if(categoryDAO.selectSetmealById(ids) > 0)
            throw new CustomException("该分类在套餐中已存在");

        categoryDAO.deleteById(ids);

        return ResEnum.SUCCESS;
    }

    @Override
    public Integer updateById(Category category) {

        categoryDAO.updateById(category);

        return ResEnum.SUCCESS;
    }

    @Override
    public List<Category> getCategoryList(Integer type) {

        if(type == null)
            return null;

        return categoryDAO.getByType(type);
    }


}
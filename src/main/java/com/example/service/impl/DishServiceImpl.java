package com.example.service.impl;

import com.example.common.ResEnum;
import com.example.dao.CategoryDAO;
import com.example.dao.DishDAO;
import com.example.dao.DishFlavorDAO;
import com.example.dto.DishDto;
import com.example.entity.Category;
import com.example.entity.Dish;
import com.example.entity.DishFlavor;
import com.example.entity.DishShow;
import com.example.exception.CustomException;
import com.example.service.CategoryService;
import com.example.service.DishFlavorService;
import com.example.service.DishService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DishServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 9:54
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishFlavorDAO dishFlavorDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public HashMap<String, Object> getByPage(Integer page, Integer pageSize, String name) {

        PageHelper.startPage(page, pageSize);
        PageInfo info = new PageInfo(dishDAO.getDishShow(name));

        HashMap<String, Object> res = new HashMap<>();
        res.put("total", (int) info.getTotal());
        res.put("records", info.getList());

        return res;
    }

    @Override
    @Transactional
    public int insertNewDish(DishDto dishDto) {

        if(dishDAO.insert(dishDto) != 1)
            throw new CustomException("新增菜品失败");

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) ->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        if(dishFlavorService.insertNewFlavor(flavors) != ResEnum.SUCCESS)
            throw new CustomException("新增口味失败");

        return ResEnum.SUCCESS;
    }

    @Override
    public DishDto getById(Long id) {

        DishDto dishDto = new DishDto();
        Dish dish = dishDAO.selectById(id);

        BeanUtils.copyProperties(dish, dishDto);

        List<DishFlavor> dishFlavors = dishFlavorDAO.selectByCategoryId(id);
        dishDto.setFlavors(dishFlavors);

        return dishDto;
    }

    @Override
    @Transactional
    public int updateById(DishDto dishDto) {

        dishDAO.updateById(dishDto);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) ->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorDAO.deleteByDishId(dishDto.getId());

        dishFlavorService.insertNewFlavor(flavors);

        return ResEnum.SUCCESS;
    }

    @Override
    public List<Dish> getByCategory(Long id) {
        return dishDAO.getByCategory(id);
    }
}

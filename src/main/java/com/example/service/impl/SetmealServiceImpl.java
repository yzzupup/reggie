package com.example.service.impl;

import com.example.common.ResEnum;
import com.example.dao.SetmealDAO;
import com.example.dao.SetmealDishDAO;
import com.example.dto.SetmealDto;
import com.example.entity.Setmeal;
import com.example.entity.SetmealDish;
import com.example.exception.CustomException;
import com.example.service.SetmealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: SetmealServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 21:31
 * @Version 1.0
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDAO setmealDAO;

    @Autowired
    private SetmealDishDAO setmealDishDAO;



    @Override
    @Transactional
    public int insertNewSetmeal(SetmealDto setmealDto) {

        setmealDAO.insert(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        for ( SetmealDish tmp : setmealDishes)
            setmealDishDAO.insert(tmp);

        return ResEnum.SUCCESS;
    }

    @Override
    public HashMap<String, Object> getByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo info = new PageInfo(setmealDAO.getAll());

        HashMap<String, Object> res = new HashMap<>();
        res.put("total", info.getTotal());
        res.put("records", info.getList());

        return res;
    }

    @Override
    @Transactional
    public Integer deleteById(Long[] ids) {

        for (Long id : ids) {
            if(setmealDAO.deleteById(id) == 0)
                throw new CustomException("不可以删除售卖中的套餐");
            setmealDishDAO.deleteBySetmealId(id);
        }

        return ResEnum.SUCCESS;
    }

}

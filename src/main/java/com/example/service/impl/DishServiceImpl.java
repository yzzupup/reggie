package com.example.service.impl;

import com.example.dao.DishDAO;
import com.example.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDAO dishDAO;
    @Override
    public HashMap<String, Object> getByPage(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        PageInfo info = new PageInfo(dishDAO.getAll(name));

        HashMap<String, Object> res = new HashMap<>();
        res.put("total", info.getSize());
        res.put("records", info.getList());
        return res;
    }
}

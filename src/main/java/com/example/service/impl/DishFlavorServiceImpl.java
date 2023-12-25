package com.example.service.impl;

import com.example.common.ResEnum;
import com.example.dao.DishFlavorDAO;
import com.example.entity.DishFlavor;
import com.example.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: DishFlavorImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 10:46
 * @Version 1.0
 */
@Service
public class DishFlavorServiceImpl implements DishFlavorService {

    @Autowired
    private DishFlavorDAO dishFlavorDAO;

    @Override
    @Transactional
    public int insertNewFlavor(List<DishFlavor> flavors) {

        for (DishFlavor flavor: flavors)
            if(dishFlavorDAO.insert(flavor) != 1)
                return ResEnum.INSERT_ERROR;

        return ResEnum.SUCCESS;
    }
}

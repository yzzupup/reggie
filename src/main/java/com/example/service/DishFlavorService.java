package com.example.service;

import com.example.entity.DishFlavor;

import java.util.List;

/**
 * ClassName: DishFlavor
 * Package: com.example.service
 * Description:
 *
 * @Author yzz
 * @Create 2023/12/6 10:46
 * @Version 1.0
 */
public interface DishFlavorService {
    int insertNewFlavor(List<DishFlavor> flavors);

}

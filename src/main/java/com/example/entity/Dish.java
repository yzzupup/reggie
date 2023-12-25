package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 菜品
 */
@Data
public class Dish implements Serializable {

    public static final long serialVersionUID = 1L;

    public Long id;


    //菜品名称
    public String name;


    //菜品分类id
    public Long categoryId;


    //菜品价格
    public BigDecimal price;


    //商品码
    public String code;


    //图片
    public String image;


    //描述信息
    public String description;


    //0 停售 1 起售
    public Integer status;


    //顺序
    public Integer sort;


    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    public Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateUser;


    //是否删除
    public Integer isDeleted;

}

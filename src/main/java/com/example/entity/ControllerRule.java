package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ControllerRule
 * Package: com.example.entity
 * Description:
 *
 * @Author yzz
 * @Create 2023/11/24 9:14
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerRule {

    private String sql;

    private String res;

}

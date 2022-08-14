package com.kimzing.mybatis.domain;

import lombok.Data;

/**
 * 车辆信息
 */
@Data
public class Car {

    private Long id;

    /**
     * 颜色
     */
    private String color;

    /**
     * 品牌
     */
    private String name;

    private Long userId;
}
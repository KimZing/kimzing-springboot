package com.kimzing.mybatis.domain.po;

import lombok.Data;

/**
 * 车辆信息
 */
@Data
public class CarPO {

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
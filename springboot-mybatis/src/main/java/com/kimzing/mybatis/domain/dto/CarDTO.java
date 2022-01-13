package com.kimzing.mybatis.domain.dto;

import lombok.Data;

/**
 * 车辆信息
 */
@Data
public class CarDTO {

    private Long id;

    /**
     * 颜色
     */
    private String color;

    /**
     * 品牌
     */
    private String name;

}
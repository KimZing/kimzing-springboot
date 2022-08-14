package com.kimzing.mybatis.domain;

import lombok.Data;

/**
 * 地址信息
 */
@Data
public class Address {

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 街道
     */
    private String street;
}
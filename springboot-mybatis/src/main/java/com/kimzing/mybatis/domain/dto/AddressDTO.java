package com.kimzing.mybatis.domain.dto;

import lombok.Data;

/**
 * 地址信息
 */
@Data
public class AddressDTO {

    private Long id;

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
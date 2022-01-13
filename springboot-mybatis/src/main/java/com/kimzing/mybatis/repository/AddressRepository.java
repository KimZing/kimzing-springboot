package com.kimzing.mybatis.repository;

import com.kimzing.mybatis.domain.po.AddressPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressRepository {
    /**
     * 根据地址id查询地址
     */
    @Select("SELECT * FROM `address` WHERE id = #{id}")
    AddressPO findAddressById(Long id);

    /**
     * 根据用户id查询地址
     */
    @Select("SELECT * FROM `address` WHERE user_id = #{userId}")
    AddressPO findAddressByUserId(Long userId);
}
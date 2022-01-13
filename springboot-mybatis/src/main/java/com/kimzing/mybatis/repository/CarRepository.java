
package com.kimzing.mybatis.repository;

import com.kimzing.mybatis.domain.po.CarPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarRepository {
    /**
     * 根据用户id查询所有的车
     */
    @Select("SELECT * FROM `car` WHERE user_id = #{userId}")
    List<CarPO> findCarsByUserId(Long userId);
}
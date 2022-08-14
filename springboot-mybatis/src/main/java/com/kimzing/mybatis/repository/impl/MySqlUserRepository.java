package com.kimzing.mybatis.repository.impl;

import com.kimzing.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据存储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/1/31 13:12
 */
@Mapper
public interface MySqlUserRepository {

    @Insert("INSERT INTO `user`(username, password, age, gender) VALUES(#{username}, #{password}, #{age}, #{gender});")
    Integer save(User user);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    void remove(Long id);

    @Update("<script>UPDATE `user` <set>" +
            "<if test='username!=null'>username = #{username},</if>" +
            "<if test='password!=null'>password = #{password},</if>" +
            "<if test='age!=null'>age = #{age},</if>" +
            "<if test='gender!=null'>gender = #{gender},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    void update(User user);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    @Results({
            @Result(property = "address", column = "id",
                    one = @One(select = "com.kimzing.mybatis.repository.AddressRepository.findAddressByUserId")),
            @Result(property = "cars", column = "id",
                    many = @Many(select = "com.kimzing.mybatis.repository.CarRepository.findCarsByUserId")),
            @Result(property = "id", column = "id")
    })
    User find(Long id);


    @Select("SELECT * FROM `user`")
    List<User> list();

}

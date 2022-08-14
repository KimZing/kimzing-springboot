package com.kimzing.mybatis.service;

import com.kimzing.mybatis.domain.User;

import java.util.List;

/**
 * 用户模拟服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:16
 */
public interface UserService {

    void save(User userDTO);

    void remove(Long id);

    void update(User user);

    User find(Long id);

    List<User> list();

}

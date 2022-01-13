package com.kimzing.mybatis.service;

import com.kimzing.mybatis.domain.dto.UserDTO;

import java.util.List;

/**
 * 用户模拟服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:16
 */
public interface UserService {

    void save(UserDTO userDTO);

    void remove(Long id);

    void update(UserDTO userDTO);

    UserDTO find(Long id);

    List<UserDTO> list();

}

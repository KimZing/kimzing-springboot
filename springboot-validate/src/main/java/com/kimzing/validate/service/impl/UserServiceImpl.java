package com.kimzing.validate.service.impl;

import com.kimzing.validate.domain.dto.UserDTO;
import com.kimzing.validate.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户模拟服务实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void getByName(String name) {
    }

    @Override
    public void getByAge(Integer ageFrom, Integer ageTo) {
    }

    @Override
    public void getByEmail(String email) {
    }

    @Override
    public void getByGender(String gender) {
    }

    @Override
    public void save(UserDTO userDTO) {
    }

    @Override
    public void update(UserDTO userDTO) {
    }

}

package com.kimzing.mybatis.service.impl;

import com.kimzing.mybatis.domain.User;
import com.kimzing.mybatis.repository.UserRepository;
import com.kimzing.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户模拟服务实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.remove(id);
    }

    @Override
    public void update(User user){
        userRepository.update(user);
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public List<User> list() {
        List<User> userList = userRepository.list();
        return userList;
    }
}

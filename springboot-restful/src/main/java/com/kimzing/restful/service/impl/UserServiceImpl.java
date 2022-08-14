package com.kimzing.restful.service.impl;

import com.kimzing.restful.domain.Query;
import com.kimzing.restful.domain.User;
import com.kimzing.restful.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户模拟服务实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public void update(User user) {
    }

    @Override
    public User find(Long id) {
        User user = new User();
        return user;
    }

    @Override
    public List<User> list(Query userQuery) {
        List<User> userList = new ArrayList<>();
        return userList;
    }
}

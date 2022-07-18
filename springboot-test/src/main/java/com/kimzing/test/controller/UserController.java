package com.kimzing.test.controller;

import com.kimzing.test.service.UserService;
import com.kimzing.test.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 11:11
 */
@Component
public class UserController {

    @Autowired
    UserService userService;

    public User saveUser(String name, Integer age, String sex) {
        return userService.saveUser(name, age, sex);
    }

    public User delete() {
        return userService.delete();
    }

    public User update() {
        return userService.update();
    }
}

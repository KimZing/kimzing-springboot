package com.kimzing.test.service.impl;

import com.kimzing.test.service.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User saveUser(String name, Integer age, String sex) {
        return new User();
    }

    public User delete() {
        return null;
    }

    public User update() {
        return null;
    }

}

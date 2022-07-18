package com.kimzing.test.service;

import com.kimzing.test.service.domain.User;

public interface UserService {

    public User saveUser(String name, Integer age, String sex);

    public User delete();

    public User update();

}

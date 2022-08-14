package com.kimzing.restful.service;

import com.kimzing.restful.domain.Query;
import com.kimzing.restful.domain.User;

import java.util.List;

/**
 * 用户模拟服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:16
 */
public interface UserService {

    void save(User user);

    void remove(Long id);

    void update(User user);

    User find(Long id);

    List<User> list(Query userQuery);

}

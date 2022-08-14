package com.kimzing.mybatis.repository;

import com.kimzing.mybatis.domain.User;
import com.kimzing.mybatis.repository.impl.MySqlUserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模拟用户存储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 11:38
 */
@Repository
public class UserRepository {

    @Resource
    MySqlUserRepository mySqlUserRepository;

    public Integer save(User user) {
        return mySqlUserRepository.save(user);
    }

    public void remove(Long id) {
        mySqlUserRepository.remove(id);
    }

    public void update(User user) {
        mySqlUserRepository.update(user);
    }

    public User find(Long id) {
        return mySqlUserRepository.find(id);
    }

    public List<User> list() {
        return mySqlUserRepository.list();
    }

}

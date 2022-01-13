package com.kimzing.mybatis.repository;

import com.kimzing.mybatis.domain.po.UserPO;
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

    public Integer save(UserPO userPO) {
        return mySqlUserRepository.save(userPO);
    }

    public void remove(Long id) {
        mySqlUserRepository.remove(id);
    }

    public void update(UserPO userPO) {
        mySqlUserRepository.update(userPO);
    }

    public UserPO find(Long id) {
        return mySqlUserRepository.find(id);
    }

    public List<UserPO> list() {
        return mySqlUserRepository.list();
    }

}

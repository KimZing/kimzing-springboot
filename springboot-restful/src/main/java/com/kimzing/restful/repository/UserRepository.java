package com.kimzing.restful.repository;

import com.kimzing.utils.page.PageResult;
import com.kimzing.restful.domain.po.UserPO;
import com.kimzing.restful.repository.impl.MockUserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 模拟用户存储.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 11:38
 */
@Repository
public class UserRepository {

    @Resource
    MockUserRepository mockUserRepository;

    public Integer save(UserPO userPO) {
        return mockUserRepository.save(userPO);
    }

    public void remove(Long id) {
        mockUserRepository.remove(id);
    }

    public void update(UserPO userPO) {
        mockUserRepository.update(userPO);
    }

    public UserPO find(Long id) {
        return mockUserRepository.find(id);
    }

    public PageResult<UserPO> list(Integer pageNum, Integer pageSize) {
        return mockUserRepository.list(pageNum, pageSize);
    }

}

package com.kimzing.restful.service.impl;

import com.kimzing.utils.bean.BeanUtil;
import com.kimzing.utils.page.PageResult;
import com.kimzing.utils.result.ApiResult;
import com.kimzing.restful.domain.dto.UserDTO;
import com.kimzing.restful.domain.dto.UserQueryDTO;
import com.kimzing.restful.domain.po.UserPO;
import com.kimzing.restful.repository.UserRepository;
import com.kimzing.restful.service.UserService;
import org.springframework.context.ApplicationContext;
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
    public void save(UserDTO userDTO) {
        UserPO userPO = BeanUtil.mapperBean(userDTO, UserPO.class);
        userRepository.save(userPO);
    }

    @Override
    public void remove(Long id) {
        userRepository.remove(id);
    }

    @Override
    public void update(UserDTO userDTO) {
        UserPO userPO = BeanUtil.mapperBean(userDTO, UserPO.class);
        userRepository.update(userPO);
    }

    @Override
    public UserDTO find(Long id) {
        UserPO userPO = userRepository.find(id);
        UserDTO userDTO = BeanUtil.mapperBean(userPO, UserDTO.class);
        return userDTO;
    }

    @Override
    public PageResult<UserDTO> list(UserQueryDTO userQuery) {
        PageResult pageResult = userRepository.list(userQuery.getPageNum(), userQuery.getPageSize());
        List<UserDTO> userDTOList = BeanUtil.mapperList(pageResult.getList(), UserDTO.class);
        pageResult.setList(userDTOList);
        return pageResult;
    }
}

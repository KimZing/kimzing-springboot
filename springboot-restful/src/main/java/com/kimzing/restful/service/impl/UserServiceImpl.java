package com.kimzing.restful.service.impl;

import com.kimzing.utils.bean.BeanUtil;
import com.kimzing.utils.page.PageResult;
import com.kimzing.utils.result.ApiResult;
import com.kimzing.restful.domain.dto.UserDTO;
import com.kimzing.restful.domain.dto.UserQueryDTO;
import com.kimzing.restful.domain.event.UserCreatedEvent;
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

    @Resource
    ApplicationContext applicationContext;

    @Override
    public ApiResult save(UserDTO userDTO) {
        UserPO userPO = BeanUtil.mapperBean(userDTO, UserPO.class);
        userRepository.save(userPO);
        userDTO = BeanUtil.mapperBean(userPO, UserDTO.class);

        // 发布领域事件: 创建用户
        applicationContext.publishEvent(new UserCreatedEvent(userDTO.getUsername()));
        return ApiResult.success();
    }

    @Override
    public ApiResult remove(Long id) {
        userRepository.remove(id);
        return ApiResult.success();
    }

    @Override
    public ApiResult update(UserDTO userDTO) {
        UserPO userPO = BeanUtil.mapperBean(userDTO, UserPO.class);
        userRepository.update(userPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult find(Long id) {
        UserPO userPO = userRepository.find(id);
        UserDTO userDTO = BeanUtil.mapperBean(userPO, UserDTO.class);
        return ApiResult.success(userDTO);
    }

    @Override
    public ApiResult list(UserQueryDTO userQuery) {
        PageResult pageResult = userRepository.list(userQuery.getPageNum(), userQuery.getPageSize());
        List<UserDTO> userDTOList = BeanUtil.mapperList(pageResult.getList(), UserDTO.class);
        pageResult.setList(userDTOList);
        return ApiResult.success(pageResult);
    }
}

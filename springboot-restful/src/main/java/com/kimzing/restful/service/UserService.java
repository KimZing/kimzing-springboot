package com.kimzing.restful.service;

import com.kimzing.utils.result.ApiResult;
import com.kimzing.restful.domain.dto.UserDTO;
import com.kimzing.restful.domain.dto.UserQueryDTO;

/**
 * 用户模拟服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:16
 */
public interface UserService {

    ApiResult save(UserDTO userDTO);

    ApiResult remove(Long id);

    ApiResult update(UserDTO userDTO);

    ApiResult find(Long id);

    ApiResult list(UserQueryDTO userQuery);

}

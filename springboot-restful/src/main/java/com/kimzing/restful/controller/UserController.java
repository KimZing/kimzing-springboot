package com.kimzing.restful.controller;

import com.kimzing.restful.domain.dto.UserDTO;
import com.kimzing.restful.domain.dto.UserQueryDTO;
import com.kimzing.restful.service.UserService;
import com.kimzing.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模拟用户控制层.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 11:37
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping
    public ApiResult save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return ApiResult.success();
    }

    @DeleteMapping("/{id}")
    public ApiResult remove(@PathVariable Long id) {
        userService.remove(id);
        return ApiResult.success();
    }

    @PutMapping("/{id}")
    public ApiResult update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        userDTO.setId(id);
        userService.update(userDTO);
        return ApiResult.success();
    }

    @GetMapping("/{id}")
    public ApiResult find(@PathVariable Long id) {
        return ApiResult.success(userService.find(id));
    }

    @PostMapping("/list")
    public ApiResult list(@RequestBody UserQueryDTO userQuery) {
        return ApiResult.success(userService.list(userQuery));
    }

}

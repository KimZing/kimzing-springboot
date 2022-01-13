package com.kimzing.mybatis.controller;

import com.kimzing.mybatis.domain.dto.UserDTO;
import com.kimzing.mybatis.service.UserService;
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
        userService.update(userDTO.setId(id));
        return ApiResult.success();

    }

    @GetMapping("/{id}")
    public ApiResult find(@PathVariable Long id) {
        return ApiResult.success(userService.find(id));
    }

    @GetMapping("/list")
    public ApiResult list() {
        return ApiResult.success(userService.list());
    }

}

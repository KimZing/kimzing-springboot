package com.kimzing.validate.controller;

import com.kimzing.utils.result.ApiResult;
import com.kimzing.validate.domain.dto.UserDTO;
import com.kimzing.validate.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模拟用户控制层.
 *
 * <p>
 *     为了模拟校验，将参数的required设置为false
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 11:37
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/name")
    public ApiResult getByName(@RequestParam(required = false) String name) {
        return userService.getByName(name);
    }

    @GetMapping("/age")
    public ApiResult getByAge(@RequestParam(required = false) Integer ageFrom, @RequestParam(required = false) Integer ageTo) {
        return userService.getByAge(ageFrom, ageTo);
    }

    @GetMapping("/email")
    public ApiResult getByEmail(@RequestParam(required = false) String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/gender")
    public ApiResult getByGender(@RequestParam(required = false) String gender) {
        return userService.getByGender(gender);
    }


    @PostMapping
    public ApiResult save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping
    public ApiResult update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

}

package com.kimzing.validate.controller;

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
    public void getByName(@RequestParam(required = false) String name) {
        userService.getByName(name);
    }

    @GetMapping("/age")
    public void getByAge(@RequestParam(required = false) Integer ageFrom, @RequestParam(required = false) Integer ageTo) {
        userService.getByAge(ageFrom, ageTo);
    }

    @GetMapping("/email")
    public void getByEmail(@RequestParam(required = false) String email) {
        userService.getByEmail(email);
    }

    @GetMapping("/gender")
    public void getByGender(@RequestParam(required = false) String gender) {
        userService.getByGender(gender);
    }


    @PostMapping
    public void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

}

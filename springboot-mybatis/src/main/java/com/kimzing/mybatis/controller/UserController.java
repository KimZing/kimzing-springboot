package com.kimzing.mybatis.controller;

import com.kimzing.mybatis.domain.User;
import com.kimzing.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("/{id}")
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

}

package com.kimzing.test.container;

import com.kimzing.test.container.config.MockContextUtil;
import com.kimzing.test.controller.UserController;
import com.kimzing.test.service.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试对service层进行mock.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 11:14
 */
@SpringBootTest
@ImportAutoConfiguration(classes = MockContextUtil.class)
public class MockTests {

    @Resource
    UserController userController;

    @Test()
    public void test() {
        User user = userController.saveUser("kimzing", 18, "MAN");

        System.out.println("添加用户" + user);
    }

    @Test()
    public void testDelete() {
        User user = userController.delete();
        System.out.println("删除用户" + user);
    }

    @Test()
    public void testUpdate() {
        User user = userController.update();
        System.out.println(user);
    }
}

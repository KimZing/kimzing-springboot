package com.kimzing.test.mock;

import com.kimzing.test.service.domain.User;
import com.kimzing.test.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

/**
 * 测试对service层进行mock.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 11:14
 */
@SpringBootTest
public class MockTests {

    @MockBean
    UserService userService;

    @BeforeEach
    public void beforeEach(){
        // ==============参数MOCK，其中后书写的优先级高========
        // 任意参数
        when(userService.saveUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenReturn(new User().setName("KimZing").setAge(18).setSex("MAN"));
        // 指定参数
        when(userService.saveUser("kimzing", 18, "MAN")).thenReturn(new User().setName("KimZing").setAge(19).setSex("MAN"));
        // 混合参数, 注意不能直接混用
        when(userService.saveUser(Mockito.anyString(), Mockito.anyInt(), Mockito.eq("WOMAN"))).thenReturn(new User().setName("KimZing").setAge(20).setSex("WOMAN"));

        // ===============方法链路============
        // 当为void方法时，啥都不做的方法
        doNothing().when(userService).delete();
    }

    @Test()
    public void test1() {
        User user = userService.saveUser("kimzing", 22, "WOMAN");
        // ===========验证==========
        // 验证次数
        verify(userService, times(1)).saveUser("kimzing", 22, "WOMAN");
        System.out.println(user);
    }



}

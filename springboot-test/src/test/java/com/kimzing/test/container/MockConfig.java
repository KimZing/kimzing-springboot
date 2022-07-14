package com.kimzing.test.container;

import com.kimzing.test.service.domain.User;
import com.kimzing.test.service.impl.UserService;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.*;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 15:05
 */
// @Configuration
public class MockConfig  implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void config(){
        UserService mock = mock(UserService.class);
        // ==============参数MOCK，其中后书写的优先级高========
        // 任意参数
        when(mock.saveUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenReturn(new User().setName("KimZing").setAge(18).setSex("MAN"));
        // 指定参数
        when(mock.saveUser("kimzing", 18, "MAN")).thenReturn(new User().setName("KimZing").setAge(19).setSex("MAN"));
        // 混合参数, 注意不能直接混用
        when(mock.saveUser(Mockito.anyString(), Mockito.anyInt(), Mockito.eq("WOMAN"))).thenReturn(new User().setName("KimZing").setAge(20).setSex("WOMAN"));

        // ===============方法链路============
        // 当为void方法时，啥都不做的方法
        doNothing().when(mock).delete();


        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext)applicationContext;
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();

        ((DefaultListableBeanFactory) beanRegistry).destroySingleton("userService");

        beanRegistry.registerSingleton("userService", mock);

        System.out.println("初始化完成");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

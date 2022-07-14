package com.kimzing.test.container;

import com.alibaba.fastjson.JSON;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 15:05
 */
// @Configuration
public class MockConfig2 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void config() throws Exception {
        String classPath = "com.kimzing.test.service.impl.UserService";
        String beanName = "";
        String methodName = "saveUser";
        String result = "{\"name\": \"kim\",\"age\": \"22\",\"sex\": \"MANN\"}";

        if (beanName == null || beanName.equals("")) {
            beanName = classPath.substring(classPath.lastIndexOf(".") + 1);
            beanName = String.valueOf(beanName.charAt(0)).toLowerCase() + beanName.substring(1);
        }

        Class clazz = Class.forName(classPath);
        Object mock = mock(clazz);
        // ==============参数MOCK，其中后书写的优先级高========
        // 任意参数
        Method saveUser = Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equals(methodName)).findFirst().get();
        int parameterCount = saveUser.getParameterCount();
        Class<?> returnType = saveUser.getReturnType();
        Object o = JSON.parseObject(result, returnType);

        Object[] params = new Object[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            params[i] = Mockito.any();
        }
        // Method saveUser = mock.getClass().getMethod("saveUser", Class.forName("java.lang.String"), Class.forName("java.lang.Integer"), Class.forName("java.lang.String"));

        // when(saveUser.invoke(mock, "kimzing", 18, "MAN")).thenReturn(new User().setName("KimZing").setAge(28).setSex("MAN"));
        // when(saveUser.invoke(mock, objects)).thenReturn(new User().setName("KimZing").setAge(28).setSex("MAN"));
        when(saveUser.invoke(mock, params)).thenReturn(o);

        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext)applicationContext;
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();

        ((DefaultListableBeanFactory) beanRegistry).destroySingleton(beanName);

        beanRegistry.registerSingleton(beanName, mock);

        System.out.println("初始化完成");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

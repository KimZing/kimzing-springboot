package com.kimzing.test.container;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 15:05
 */
@Configuration
public class MockConfig3 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void config() throws Exception {
        URL resource = MockConfig3.class.getClassLoader().getResource("mock.json");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        String s = new String(bytes, Charset.forName("utf-8"));
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(s);

        String classPath = jsonObject.getString("class");
        String beanName =  jsonObject.getString("bean");
        if (beanName == null || beanName.equals("")) {
            beanName = classPath.substring(classPath.lastIndexOf(".") + 1);
            beanName = String.valueOf(beanName.charAt(0)).toLowerCase() + beanName.substring(1);
        }
        Class clazz = Class.forName(classPath);
        Object mock = mock(clazz);


        JSONArray mocks = jsonObject.getJSONArray("mocks");
        for (int i = 0; i < mocks.size(); i++) {
            String methodName = mocks.getJSONObject(i).getString("method");
            String returnString = mocks.getJSONObject(i).getString("return");
            mockObjectMethod(clazz, mock, methodName, returnString);
        }


        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext)applicationContext;
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();

        ((DefaultListableBeanFactory) beanRegistry).destroySingleton("userService");

        beanRegistry.registerSingleton("userService", mock);

        System.out.println("初始化完成");
    }

    private void mockObjectMethod(Class mockClass, Object mockObject, String methodName, String returnString) throws Exception {
        // ==============参数MOCK，其中后书写的优先级高========
        // 任意参数
        Method saveUser = Arrays.stream(mockClass.getMethods()).filter(m -> m.getName().equals(methodName)).findFirst().get();
        int parameterCount = saveUser.getParameterCount();
        Class<?> returnType = saveUser.getReturnType();
        Object o = JSON.parseObject(returnString, returnType);

        Object[] objects = new Object[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            objects[i] = Mockito.any();
        }
        when(saveUser.invoke(mockObject, objects)).thenReturn(o);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

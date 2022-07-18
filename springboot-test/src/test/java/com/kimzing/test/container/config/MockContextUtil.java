package com.kimzing.test.container.config;

import com.kimzing.test.container.dto.MethodInfo;
import com.kimzing.test.container.dto.MockBeanDTO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 15:05
 */
public class MockContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @PostConstruct
    private void before() {
        MockBeanDTO mockBeanDTO = new MockBeanDTO();
        mockBeanDTO.setClasspath("com.kimzing.test.service.UserService");
        mockBeanDTO.setBeanName("userService");
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethod("saveUser");
        methodInfo.setResult("{\"name\": \"mock\", \"age\":  8, \"sex\": \"OTHER\"}");
        mockBeanDTO.setMethods(Arrays.asList(methodInfo));

        MockBeanUtil.mockBean(mockBeanDTO);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

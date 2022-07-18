package com.kimzing.test.container.dto;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 *  mock信息.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/18 14:31
 */
public class MockBeanDTO {
    /** mock类的全路径 */
    @NonNull
    private String classpath;
    /** mock类在容器中的id */
    @Nullable
    private String beanName;
    /** mock的方法集合 */
    @NonNull
    private List<MethodInfo> methods;

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }
}



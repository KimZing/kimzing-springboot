package com.kimzing.test.container.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * mock信息.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/18 14:33
 */
public class MethodInfo {
    /** 方法名 */
    @NonNull
    private String method;

    /** 方法参数的完整类型列表 */
    @Nullable
    private List<String> paramTypes;

    /** 1. 普通字符串  2. JSON对象  3. JSON数组 */
    @Nullable
    private String result;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

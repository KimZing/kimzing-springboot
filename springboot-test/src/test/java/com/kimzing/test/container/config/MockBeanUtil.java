package com.kimzing.test.container.config;

import com.alibaba.fastjson.JSON;
import com.kimzing.test.container.dto.MethodInfo;
import com.kimzing.test.container.dto.MockBeanDTO;
import org.apache.commons.lang3.StringUtils;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Mock工具.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/18 15:34
 */
public class MockBeanUtil {

    private static final Logger log = LoggerFactory.getLogger(MockBeanUtil.class);

    /**
     * 批量mock bean
     * <p>
     *     注意要在容器初始化测试类前进行调用
     * </p>
     *
     * @param beans
     */
    public static void mockBeans(List<MockBeanDTO> beans) {
        beans.forEach(b -> mockBean(b));
    }

    /**
     * 根据元信息，mock容器中的bean
     * <p>
     *     注意要在容器初始化测试类前进行调用
     * </p>
     * @param bean
     * @throws Exception
     */
    public static void mockBean(MockBeanDTO bean) {
        // 检查并完善元信息
        if (!check(bean) || !complete(bean)) {
            throw new IllegalArgumentException("mock信息参数校验失败");
        }

        // 生成mock对象
        Object mockBean = createMockBean(bean);

        // 替换Spring容器中的bean
        replaceContextBean(bean.getBeanName(), mockBean);
    }

    private static Object createMockBean(MockBeanDTO bean) {
        try {
            Class clazz = Class.forName(bean.getClasspath());
            Object mockBean = mock(clazz);
            for (MethodInfo method : bean.getMethods()) {
                Class sourceClazz = clazz;
                mockMethod(sourceClazz, mockBean, method);
            }
            return mockBean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void mockMethod(Class sourceClazz, Object mockBean, MethodInfo methodInfo) throws Exception {
        // 获取对应的方法
        Method method = getMethodByParamTypes(sourceClazz, methodInfo);
        int parameterCount = method.getParameterCount();
        Class<?> returnType = method.getReturnType();
        Object returnObject = null;
        // 如果返回类型为集合或数组类型使用JSONArray转换
        if (returnType.isArray()) {
            returnObject = JSON.parseArray(methodInfo.getResult(), getMethodReturnValueGeneric(method)).toArray();
        } else if(returnType.newInstance() instanceof Collection) {
            returnObject = JSON.parseArray(methodInfo.getResult(), getMethodReturnValueGeneric(method));
        }else {
            returnObject = JSON.parseObject(methodInfo.getResult(), returnType);
        }

        Object[] mockParams = new Object[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            mockParams[i] = Mockito.any();
        }
        when(method.invoke(mockBean, mockParams)).thenReturn(returnObject);
    }

    private static Class getMethodReturnValueGeneric(Method method) throws NoSuchMethodException {
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericReturnType;
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                Class typeArgClass = (Class) actualTypeArgument;
                return typeArgClass;
            }
        }
        return null;
    }

    private static Method getMethodByParamTypes(Class sourceClazz, MethodInfo method) throws ClassNotFoundException {
        Method matchMethod = null;
        // 参数列表为空时，获取第一个同名方法
        if (CollectionUtils.isEmpty(method.getParamTypes())) {
            matchMethod = Arrays.stream(sourceClazz.getDeclaredMethods()).filter(m -> m.getName().equals(method.getMethod())).findFirst().get();
        } else {
            Method[] declaredMethods = sourceClazz.getDeclaredMethods();
            // 获取所有方法并比对方法的参数与期望参数匹配的方法
            OUTER:
            for (Method declaredMethod : declaredMethods) {
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                if (parameterTypes.length != method.getParamTypes().size()) {
                    continue OUTER;
                }
                // 比对参数的类型列表
                INNER:
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (parameterTypes[i] != Class.forName(method.getParamTypes().get(i))) {
                        break INNER;
                    }
                    if (i == parameterTypes.length - 1) {
                        matchMethod = declaredMethod;
                    }
                }
            }
        }

        if (matchMethod == null) {
            throw new RuntimeException(String.format("找不到对应的mock方法：%s", method.getMethod()));
        }
        return matchMethod;
    }

    private static boolean complete(MockBeanDTO bean) {
        String beanName = bean.getBeanName();
        // 如果beanName为空，更改为类名首字母小写
        if (StringUtils.isBlank(beanName)) {
            beanName = new StringBuilder(Character.toLowerCase(beanName.charAt(0)))
                    .append(beanName.substring(1))
                    .toString();
            bean.setBeanName(beanName);
        }
        //

        return true;
    }

    private static boolean check(MockBeanDTO bean) {
        // 判断类相关
        if (StringUtils.isBlank(bean.getClasspath()) || !bean.getClasspath().contains(".")) {
            log.error("class 路径填写错误");
            return false;
        }
        // 判断方法
        if (CollectionUtils.isEmpty(bean.getMethods())) {
            log.error("缺少需要mock的方法列表");
            return false;
        }
        // 判断方法内的方法名
        if (bean.getMethods().stream().filter(m -> StringUtils.isBlank(m.getMethod())).count() > 0) {
            log.error("请填写需要mock的方法名");
            return false;
        }
        return true;
    }

    private static void replaceContextBean(String beanName, Object mockBean) {
        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) MockContextUtil.getApplicationContext();
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();
        // 销毁原有bean
        ((DefaultListableBeanFactory) beanRegistry).destroySingleton(beanName);
        // 注册mock bean
        beanRegistry.registerSingleton(beanName, mockBean);

        log.info("[{}] mock 完成", beanName);
    }

}

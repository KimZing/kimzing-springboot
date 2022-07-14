package com.kimzing.test.basic;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 基本使用.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 11:14
 */
@SpringBootTest
public class BasicTests {

    /**
     * 先于所有用例，只执行一次，且必须是static方法
     */
    @BeforeAll
    public static void beforeAll(){
        System.out.println("类级别初始化");
    }

    /**
     * 先于每个测试用例
     */
    @BeforeEach
    public void beforeEach(){
        System.out.println("每个测试方法都会执行一遍");
    }

    @Test()
    public void test1() {
        System.out.println("ceshi1");
    }

    @Test
    public void test2() {
        System.out.println("ceshi1");
    }


    /**
     * 后于每个测试用例
     */
    @AfterEach
    public void afterEach(){
        System.out.println("每个测试方法执行后都会执行一遍");
    }

    /**
     * 后于所有用例，只执行一次，且必须是static方法
     */
    @AfterAll
    public static void afterAll(){
        System.out.println("类级别销毁");
    }



}

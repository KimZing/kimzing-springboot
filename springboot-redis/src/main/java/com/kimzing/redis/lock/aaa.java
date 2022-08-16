package com.kimzing.redis.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/16 11:13
 */
public class aaa {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        //释放semaphore 才能后面判断为true
        if (semaphore.tryAcquire(10, TimeUnit.SECONDS)) {
            System.out.println("10s过后");
        }
        System.out.println("1");
    }
}

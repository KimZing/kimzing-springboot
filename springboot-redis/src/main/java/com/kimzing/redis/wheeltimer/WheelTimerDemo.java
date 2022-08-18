package com.kimzing.redis.wheeltimer;

import io.netty.util.HashedWheelTimer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 时间轮示例.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/17 09:21
 */
@RestController
@RequestMapping("/wheeltimer")
public class WheelTimerDemo {

    // 默认：指针100ms转一格，一圈有512格
    // HashedWheelTimer timer = new HashedWheelTimer();
    HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 100);

    @GetMapping("/test/schedule")
    public void test(){
        // 提交一个任务，10s后执行
        timer.newTimeout(timeout -> System.out.println(LocalDateTime.now() + "执行"), 10, TimeUnit.SECONDS);
    }

    /**
     * 周期执行
     */
    @GetMapping("/test/wheel")
    public void test2(){
        timer.newTimeout(timeout -> {
            System.out.println(LocalDateTime.now());
            System.out.println("周期任务，5s执行一次");
            test2();
        }, 5, TimeUnit.SECONDS);
    }
}

package com.kimzing.validate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
class ValidateApplicationTests {

    @Resource
    MockMvc mockMvc;

    @Test
    public void contextLoad() {

    }

}

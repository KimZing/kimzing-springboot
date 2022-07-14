package com.kimzing.test.service.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
 
    private String name;
 
    private String sex;
 
    private Integer age;
 
}
package com.kimzing.restful.domain;

import lombok.Data;

/**
 * 用户信息实体.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 12:52
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer age;

    private GenderEnum gender;

}

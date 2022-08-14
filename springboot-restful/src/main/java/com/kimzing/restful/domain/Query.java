package com.kimzing.restful.domain;

import lombok.Data;

/**
 * 用户列表查询条件.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/1/14 13:26
 */
@Data
public class Query {

    private Integer pageNum;

    private Integer pageSize;

    private Integer ageFrom;

    private Integer ageTo;

}

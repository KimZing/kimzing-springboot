package com.kimzing.restful.domain.dto;

import lombok.Data;

/**
 * 用户列表查询条件.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/1/14 13:26
 */
@Data
public class UserQueryDTO {

    private Integer pageNum;

    private Integer pageSize;

    private Integer ageFrom;

    private Integer ageTo;

}

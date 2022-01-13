package com.kimzing.mybatis.domain.po;

import com.kimzing.utils.po.AbstractPO;
import com.kimzing.mybatis.domain.vo.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户信息实体.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 12:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPO extends AbstractPO {

    private String username;

    private String password;

    private Integer age;

    private GenderEnum gender;

    /**
     * 地址信息，和用户是一对一的关系
     */
    private AddressPO address;

    /**
     * 用户拥有的车，和用户是一对多的关系
     */
    private List<CarPO> cars;

}

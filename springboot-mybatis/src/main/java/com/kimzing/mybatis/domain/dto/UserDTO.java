package com.kimzing.mybatis.domain.dto;

import com.kimzing.mybatis.domain.vo.GenderEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户信息实体.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 12:52
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private Integer age;

    private GenderEnum gender;

    private AddressDTO address;

    private List<CarDTO> cars;

}

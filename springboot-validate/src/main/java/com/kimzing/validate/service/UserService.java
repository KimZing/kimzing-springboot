package com.kimzing.validate.service;

import com.kimzing.validate.config.group.SaveValidGroup;
import com.kimzing.validate.config.group.UpdateValidGroup;
import com.kimzing.validate.config.valid.Gender;
import com.kimzing.validate.domain.dto.UserDTO;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 用户模拟服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019/12/28 16:16
 */
@Validated
public interface UserService {

    void getByName(@NotBlank(message = "用户名不能为空") @Length(min = 1, max = 8, message = "用户名长度不符") String name);

    void getByAge(@Min(value = 1, message = "年龄起始范围不正确") Integer ageFrom, @Max(value = 150, message = "年龄结束范围不正确") Integer ageTo);

    void getByEmail(@Email(message = "邮箱格式不正确") String email);

    void save(@Validated(value = SaveValidGroup.class) UserDTO userDTO);

    void update(@Validated(value = UpdateValidGroup.class) UserDTO userDTO);

    void getByGender(@Gender(message = "性别错误") String gender);
}

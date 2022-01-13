package com.kimzing.validate.service;

import com.kimzing.utils.group.SaveValidGroup;
import com.kimzing.utils.group.UpdateValidGroup;
import com.kimzing.utils.result.ApiResult;
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

    ApiResult getByName(@NotBlank(message = "1001") @Length(min = 1, max = 8, message = "1002") String name);

    ApiResult getByAge(@Min(value = 1, message = "1003") Integer ageFrom, @Max(value = 150, message = "1004") Integer ageTo);

    ApiResult getByEmail(@Email(message = "1005") String email);

    ApiResult save(@Validated(value = SaveValidGroup.class) UserDTO userDTO);

    ApiResult update(@Validated(value = UpdateValidGroup.class) UserDTO userDTO);

    ApiResult getByGender(@Gender(message = "1008") String gender);
}

package com.kimzing.validate.config.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 性别校验.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/1/15 17:51
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidate.class)
public @interface Gender {

    String message() default "gender is illegal";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

package com.kimzing.validate.config.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidate implements ConstraintValidator<Gender, String> {

   @Override
   public void initialize(Gender gender) { }

   @Override
   public boolean isValid(String gender, ConstraintValidatorContext context) {
      return "MAN".equals(gender) || "WOMAN".equals(gender) || "SECRET".equals(gender);
   }

}
package com.lhy.systemdemo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description:
 *
 * @since 2020/8/19 15:22
 */
public class MoneyValidator implements ConstraintValidator<Money,String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}

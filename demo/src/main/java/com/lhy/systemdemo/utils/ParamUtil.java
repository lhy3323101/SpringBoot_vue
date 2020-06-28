package com.lhy.systemdemo.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lhy.systemdemo.base.exception.ParamIllegalException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * Description:参数校验配合valid注解使用
 *
 * @author Angel
 * @since 2020/5/29 15:18
 */
public class ParamUtil {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);
        if (constraintViolations.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap map = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> next = iterator.next();
                map.put(next.getPropertyPath(), next.getMessage());
            }
            return map;
        }
    }

    private static Map<String, String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map<String, String> errors = Maps.newLinkedHashMap();
        do {
            if (!iterator.hasNext()) {
                return errors;
            }
            Object next = iterator.next();
            errors = validate(next);
        } while (errors.isEmpty());
        return errors;
    }

    private static Map<String, String> validateObject(Object first,Object... objects) {
        if (objects!=null&&objects.length>0){
            return validateList(Lists.asList(first,objects));
        }else{
            return validate(first);
        }
    }

    public static void checkParam(Object param){
        Map<String, String> errors = validateObject(param);
        if (errors!=null && !errors.isEmpty()){
            throw new ParamIllegalException(errors.toString());
        }
    }
}

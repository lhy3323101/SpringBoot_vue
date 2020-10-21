package com.lhy.systemdemo.filter;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.validation.*;
import javax.validation.groups.Default;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @since 2020/10/9 10:24
 */
@WebFilter(urlPatterns = {"/*"},filterName = "CheckParamFilter")
@Order(2)
@Component
public class CheckParamFilter implements Filter {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> paramsMap =  servletRequest.getParameterMap();
        Map<String, String> paramMap = new HashMap<>();
        for (String key : paramsMap.keySet()) {
            String value = null;
            if (paramsMap.get(key) != null){
                value = paramsMap.get(key)[0];
            }
            paramMap.put(key,value);
        }
        if (paramMap.size() > 0){
            checkParam(paramMap);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 校验入参
     * @param param
     * @return
     */
    private boolean checkParam(Map<String,String> param){
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Map<String,String>>> constrainSet = validator.validate(param, Default.class);
        if (CollectionUtils.isEmpty(constrainSet)){
            return true;
        }
        ConstraintViolation<Map<String,String>> violation = constrainSet.iterator().next();
        String firstMsg = violation.getMessage();
        Path path = violation.getPropertyPath();
        if (path == null){
            throw new RuntimeException(firstMsg);
        }else {
            throw new RuntimeException(path.toString() + firstMsg);
        }
    }
}


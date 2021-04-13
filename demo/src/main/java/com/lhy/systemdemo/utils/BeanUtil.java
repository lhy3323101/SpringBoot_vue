package com.lhy.systemdemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2021/1/23 18:34
 */
public class BeanUtil implements ApplicationContextAware {

    private static volatile Map<String, Object> beanMap = new ConcurrentHashMap<>(128);

    private static ApplicationContext applicationContext;


    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BeanUtil.applicationContext == null) {
            BeanUtil.applicationContext = applicationContext;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> t){
        String name = t.getName();
        if (beanMap.get(name) != null){
            return (T)beanMap.get(name);
        }
        T classBean = (T)getApplicationContext().getBean(t);
        beanMap.put(name,classBean);
        return classBean;

    }
}

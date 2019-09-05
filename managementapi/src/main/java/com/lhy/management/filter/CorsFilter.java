package com.lhy.management.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Description: 跨域过滤器
 *
 * @author Angel
 * @since 2019/8/28 17:40
 */
@Configuration
public class CorsFilter extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }

}

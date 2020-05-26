package com.lhy.systemdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/5/25 16:55
 */
@Configuration
public class MyWebConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/book/file/**").addResourceLocations("file:" + "D:/Person/Project/front_back/img/");
    }
}

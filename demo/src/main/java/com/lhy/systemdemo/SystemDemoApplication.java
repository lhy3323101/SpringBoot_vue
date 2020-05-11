package com.lhy.systemdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lhy.systemdemo.dao")
public class SystemDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemDemoApplication.class, args);
	}

}

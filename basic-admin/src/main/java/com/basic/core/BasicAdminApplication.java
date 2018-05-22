package com.basic.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * admin的启动入口
 * @author: wangzw
 * @version: 1.0
 * @date: 2018/5/22 15:56
 */
@ImportResource(locations = {"classpath:dubbo-*.xml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BasicAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicAdminApplication.class, args);
	}
}

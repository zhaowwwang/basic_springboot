package com.basic.core;

import com.basic.core.datasource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author: wangzw
 * @version: 1.0
 * @date: 2018/5/21 17:39
 */
@MapperScan("com.basic.core.mapper")
@ImportResource(locations = {"classpath:dubbo-*.xml"})
@Import(DynamicDataSourceRegister.class)
@EnableAutoConfiguration
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
}

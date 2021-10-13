package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liujq
 * @create 2021-09-24 10:06
 */
@SpringBootApplication
@MapperScan("com.imooc.mapper")
//扫描所有包及相关组件包
@ComponentScan(basePackages = {"com.imooc","org.n3r.idworker"})
public class ApiApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class, args);
    }
}

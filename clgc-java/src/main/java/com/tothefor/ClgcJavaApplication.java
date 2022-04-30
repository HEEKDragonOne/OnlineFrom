package com.tothefor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.tothefor.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class ClgcJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClgcJavaApplication.class, args);
    }

}

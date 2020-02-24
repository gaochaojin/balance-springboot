package com.gaochaojin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gaochaojin"})
@MapperScan(basePackages = {"com.gaochaojin.dao"})
public class BalanceSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalanceSpringbootApplication.class, args);
    }

}

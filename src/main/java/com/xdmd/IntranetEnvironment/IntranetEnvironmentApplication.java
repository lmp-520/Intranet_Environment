package com.xdmd.IntranetEnvironment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xdmd.IntranetEnvironment.*.mapper"})
public class IntranetEnvironmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntranetEnvironmentApplication.class,args);
    }
}

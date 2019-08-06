package com.xdmd.IntranetEnvironment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.xdmd.IntranetEnvironment.*.mapper"})
@EnableSwagger2
public class IntranetEnvironmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntranetEnvironmentApplication.class,args);
    }
}

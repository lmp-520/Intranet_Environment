package com.xdmd.IntranetEnvironment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.xdmd.IntranetEnvironment.*.mapper"})
@EnableSwagger2
public class IntranetEnvironmentApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(IntranetEnvironmentApplication.class, args);
    }


   //打jar包时注释这个方法和【extends SpringBootServletInitializer】
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       return builder.sources(IntranetEnvironmentApplication.class);
   }

}

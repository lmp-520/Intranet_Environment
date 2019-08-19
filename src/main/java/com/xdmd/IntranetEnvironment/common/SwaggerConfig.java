package com.xdmd.IntranetEnvironment.common;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * @version V1.0
 * @Title: Swagger配置类
 * @ClassName: com.newcapec.config.swagger.Swagger2Configuration.java
 * @Description:
 * @Copyright 2019/8 -- Powered By 研发部
 * @author: Kong
 * @date:2019-12-11
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置docket以配置Swagger具体参数
     * @return
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                /**
                 *  错误路径不监控
                 */
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Kong", "暂无", "kong_9527@outlook.com");
        return new ApiInfoBuilder()
                .title("Environment_API接口文档")
                .description("环保科研课题系统接口文档")
                .contact(contact)
                .version("v1.0.0")
                .build();
    }
}

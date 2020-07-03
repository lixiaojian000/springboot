package com.example.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //添加配置类
@EnableSwagger2 //开启Swagger2
public class Swagger2Config {

    @Bean
    public Docket docket1(){ return new Docket(DocumentationType.SWAGGER_2) .groupName("A");}


    @Bean
    public Docket docket2(){ return new Docket(DocumentationType.SWAGGER_2) .groupName("B");}
    //配置swagger的bean实例

    @Bean
    public Docket docket(Environment environment){
        //设置要启动Swagger的运行环境
        Profiles profiles =Profiles.of("dev");
        //通过environment.acceptsProfiles判断当前是否处在自己设置的运行环境中
        boolean flag=environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("晓剑")
                .enable(true)//通过flag，判断是否启动Swagger
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.example.pms.controller"))
                .build();
    }
    //配置swagger信息
    public ApiInfo apiInfo(){
        //作者信息
       Contact contact = new Contact("李晓剑", "", "");
        return new ApiInfo(
                "晓剑",
                "Api Documentation",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}

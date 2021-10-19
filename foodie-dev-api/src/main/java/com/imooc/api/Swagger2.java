package com.imooc.api;

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
 * @author liujinqiang
 * @create 2021-10-14 10:57
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    //http://localhost:8088/swagger-ui.html  swagger原访问地址
    //http://localhost:8088/doc.html  swagger新文档访问地址
    //配置swagger2核心配置
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))  //扫描包指定controller
                .paths(PathSelectors.any()) //所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口api") //文档页标题
                .contact(new Contact("imooc",
                        "https://www.imooc.com",
                        "1429280405@qq.com"))   //联系人方式
                .description("专为天天吃货提供的api文档")  //详细信息
                .version("1.0.1")   //版本号
                .termsOfServiceUrl("https://www.imooc.com") //网站地址
                .build();
    }

}

package com.example.wby.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = {"enable"}, havingValue = "true")
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

    @Bean
    public Docket createInternalInterfaceApi() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        return new Docket(DocumentationType.SWAGGER_2).globalResponseMessage(RequestMethod.GET, responseMessageList).globalResponseMessage(RequestMethod.POST, responseMessageList).groupName("内部接口").apiInfo(internalInterfaceApiInfo()).enable(true).select().apis(RequestHandlerSelectors.basePackage("com.example.wby")).paths(PathSelectors.regex(".*/ui/.*")).build();
    }

    private ApiInfo internalInterfaceApiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("xx内部API文档")
                //创建人
                .contact(new Contact("xx开发组", "", "wubinyu@xx.com"))
                //版本号
                .version("1.1.0")
                //描述
                .description("xx前后端联调接口文档").build();
    }

    @Bean
    public Docket createExternalInterfaceApi() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        return new Docket(DocumentationType.SWAGGER_2).globalResponseMessage(RequestMethod.GET, responseMessageList).globalResponseMessage(RequestMethod.POST, responseMessageList).groupName("外部接口").apiInfo(externalInterfaceApiInfo()).enable(true).select().apis(RequestHandlerSelectors.basePackage("com.example.wby")).paths(PathSelectors.regex(".*/api/.*")).build();
    }

    private ApiInfo externalInterfaceApiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("xx对外提供的API接口文档")
                //创建人
                .contact(new Contact("xx开发组", "", "wubinyu@xx.com"))
                //版本号
                .version("1.1.0")
                //描述
                .description("xxxxx").build();
    }
}

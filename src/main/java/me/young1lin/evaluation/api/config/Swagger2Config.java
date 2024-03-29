package me.young1lin.evaluation.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author young1lin
 * @version 1.0
 * @date 2019/11/2 7:14 上午
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class Swagger2Config {

    @Value("${swagger2.enable}")
    private String enable;

    @Value("${server.port}")
    private String port;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(input -> "true".equals(enable))
                .apis(RequestHandlerSelectors.basePackage("me.young1lin.evaluation.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        Contact contact = new Contact("young1lin","127.0.0.1","young1lin0108@gmail.com");
        return new ApiInfoBuilder()
                .title("评价系统的api")
                .description("评价系统的api")
                .termsOfServiceUrl("http://127.0.0.1:"+port)
                .contact(contact)
                .version("1.0")
                .build();
    }
}

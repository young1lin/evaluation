package me.young1lin.evaluation.pages.config;

import me.young1lin.evaluation.pages.controller.IndexController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:young1lin0108@gmail.com"></a>young1lin
 * @version 1.0
 * @since 2020/11/16 10:15 上午
 */
@Configuration
public class ControllerAutoConfiguration {

    @Bean
    public IndexController indexController(){
        return new IndexController();
    }

}

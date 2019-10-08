package cn.luckyray.evaluation.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author young1Lin
 * @description
 * @date 2019/9/29 13:16
 * @github www.github.com/young1lin
 */
@Configuration
public class CROSConfirguration extends WebMvcConfigurerAdapter {
    /**
     * 处理AJAX请求跨域的问题
     * @author Levin
     * @time 2017-07-13
     */
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}


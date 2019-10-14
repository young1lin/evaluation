package cn.luckyray.evaluation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author young1Lin
 * @date 2019/9/29 13:16
 */
@Configuration
public class CROSConfiguration extends WebMvcConfigurerAdapter {
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


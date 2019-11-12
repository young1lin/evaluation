package cn.luckyray.evaluation.config;

import cn.luckyray.evaluation.interceptor.AccessInterceptor;
import cn.luckyray.evaluation.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加拦截器
 * @author young1Lin
 * @GitHub www.github.com/young1lin
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AccessInterceptor accessInterceptor(){
        return new AccessInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>(2);
        excludePathPatterns.add("/js/*.js");
        excludePathPatterns.add("/css/*.css");
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns)
                .order(1);
        registry.addInterceptor(accessInterceptor())
                .addPathPatterns("/api/*")
                .excludePathPatterns(excludePathPatterns)
                .order(0);
    }
}

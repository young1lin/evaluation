package me.young1lin.evaluation.api.config;

import me.young1lin.evaluation.api.interceptor.AccessInterceptor;
import me.young1lin.evaluation.api.interceptor.AccessLogInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加拦截器
 * @author <a>mailto:young1lin0108@gmail.com>young1Lin</a>
 */
@Configuration
@Profile({"dev", "prod"})
public class InterceptorConfig implements WebMvcConfigurer {

	private static final String DEV_PROFILE = "dev";

	@Value("${spring.profiles.active:dev}")
	private String profile;


	@Bean
	public AccessInterceptor accessInterceptor() {
		return new AccessInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePathPatterns = new ArrayList<>(2);
		excludePathPatterns.add("/js/*.js");
		excludePathPatterns.add("/css/*.css");
		if (DEV_PROFILE.equals(profile)) {
			registry.addInterceptor(new AccessLogInterceptor())
					.addPathPatterns("/**")
					.excludePathPatterns(excludePathPatterns)
					.order(-1);
		}
		if (!("prod".equals(profile))) {
			registry.addInterceptor(accessInterceptor())
					.addPathPatterns("/api/*")
					.excludePathPatterns(excludePathPatterns)
					.order(0);
		}
	}

}

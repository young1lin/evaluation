package me.young1lin.evaluation.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author young1Lin
 * @date 2019/9/29 13:16
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	/**
	 * 处理AJAX请求跨域的问题
	 */
	private static final String[] ORIGINS = new String[] {"GET", "POST", "PUT", "DELETE"};


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods(ORIGINS)
				.maxAge(3600);
	}

}


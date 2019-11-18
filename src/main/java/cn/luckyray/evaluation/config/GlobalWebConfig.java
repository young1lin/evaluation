package cn.luckyray.evaluation.config;

import cn.luckyray.evaluation.filter.ApiFilter;
import cn.luckyray.evaluation.filter.InitServletHolder;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 8:34
 **/
@Configuration
public class GlobalWebConfig {

    @Value("${server.port}")
    private String port;

    /**
     * 添加Enjoy模版引擎
     * @author 杨逸林
     * @date 2019-07-10 8:43
     * @return com.jfinal.template.ext.spring.JFinalViewResolver
    */
    @Bean(name = "jfinalViewResolver")
    public JFinalViewResolver getJFinalViewResolver() throws UnknownHostException {
        //获取本地ip，和端口，并将信息拼接设置成context
        String ip = InetAddress.getLocalHost().getHostAddress();
        String localIp = ip+":"+port;
        JFinalViewResolver jfr = new JFinalViewResolver();
        // setDevMode 配置放在最前面
        jfr.setDevMode(true);
        // 使用 ClassPathSourceFactory 从 class path 与 jar 包中加载模板文件
        jfr.setSourceFactory(new ClassPathSourceFactory());
        // 在使用 ClassPathSourceFactory 时要使用 setBaseTemplatePath
        JFinalViewResolver.engine.setBaseTemplatePath("/templates/");
        JFinalViewResolver.engine.addSharedObject("context",localIp);
        jfr.setSuffix(".html");
        jfr.setContentType("text/html;charset=UTF-8");
        jfr.setOrder(0);
        return jfr;
    }

    /**
     * 添加 WebSocket 支持
     * @author 杨逸林
     * @date 2019/7/3 9:20
     * @return org.springframework.web.socket.server.standard.ServerEndpointExporter
    */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 添加 FastJson 支持
     * @author 杨逸林
     * @date 2019/7/3 11:16
     * @return org.springframework.boot.autoconfigure.http.HttpMessageConverters
    */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3. 在converter中添加配置信息
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fasHttpMessageConverter);
    }

    @Bean
    public FilterRegistrationBean apiFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ApiFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/api/*"));
        return filterRegistrationBean;
    }

    @Bean

    public FilterRegistrationBean initFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new InitServletHolder());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}

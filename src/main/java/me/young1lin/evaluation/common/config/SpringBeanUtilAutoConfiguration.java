package me.young1lin.evaluation.common.config;

import me.young1lin.evaluation.common.util.SpringBeanUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @version 1.0
 * @since 2020/11/16 10:05 上午
 */
@Configuration
public class SpringBeanUtilAutoConfiguration {

    @ConditionalOnClass(SpringBeanUtil.class)
    @Bean
    public SpringBeanUtil springBeanUtil(){
        return new SpringBeanUtil();
    }

}

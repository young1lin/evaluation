package cn.luckyray.evaluation.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author young1Lin
 * @description
 * @date
 * @GitHub www.github.com/young1lin
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LogAnnotation {
    String logInfo() default "default log info";
}

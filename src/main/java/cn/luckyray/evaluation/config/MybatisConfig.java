package cn.luckyray.evaluation.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author young1Lin
 */
@Configuration
@MapperScan(basePackages = "cn.luckyray.evaluation.mapper" ,sqlSessionTemplateRef = "mainSqlSessionTemplate")
public class MybatisConfig{

}

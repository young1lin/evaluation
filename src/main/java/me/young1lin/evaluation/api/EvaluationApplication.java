package me.young1lin.evaluation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.function.UnaryOperator;

/**
 * 不需要自动装配数据源，事务管理，JdbcTemplate
 *
 * @author 杨逸林
 * @date 2019/7/2 14:39
 */
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
public class EvaluationApplication {

	private static UnaryOperator<Object> list = (t) -> t;

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}
}


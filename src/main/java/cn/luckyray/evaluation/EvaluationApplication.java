package cn.luckyray.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * TODO
 * @author 杨逸林
 * @date 2019/7/2 14:39
*/
@ServletComponentScan
@SpringBootApplication
@EnableAspectJAutoProxy
public class EvaluationApplication {
	public static void main(String[] args){ SpringApplication.run(EvaluationApplication.class, args); }
}

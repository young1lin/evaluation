package cn.luckyray.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * TODO
 * @author 杨逸林
 * @date 2019/7/2 14:39
*/
@ServletComponentScan
@SpringBootApplication
public class EvaluationApplication {
	public static void main(String[] args){ SpringApplication.run(EvaluationApplication.class, args); }
}

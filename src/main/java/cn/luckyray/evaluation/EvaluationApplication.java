package cn.luckyray.evaluation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * TODO
 * @author 杨逸林
 * @date 2019/7/2 14:39
*/
@SpringBootApplication
@MapperScan("cn.luckyray.evaluation.mapper")
public class EvaluationApplication {
	public static void main(String[] args){ SpringApplication.run(EvaluationApplication.class, args); }
}

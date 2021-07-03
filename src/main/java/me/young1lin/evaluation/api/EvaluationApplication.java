package me.young1lin.evaluation.api;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;

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
//@Import({MyBeanDefinitionBeanPostProcessor.class})
public class EvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] {EvaluationApplication.class}, args);
	}

}

/**
 * 注意，内部类的注解是获取不到的，只能获取 public class 的注解。当然这里加 Component 能扫描到，感觉这是 Spring 的一个缺陷吧。
 * {@link ConfigurationClassPostProcessor} 这个类的缺陷。
 */
class MyBeanDefinitionBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, ImportBeanDefinitionRegistrar {

	private final StopWatch watch = new StopWatch();

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("=======================11111111=");
		watch.start();
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=======================222222222=");
		watch.stop();
		System.out.println("wa" + watch.getTotalTimeSeconds());
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		MultiValueMap<String, Object> map = importingClassMetadata.getAllAnnotationAttributes(Component.class.getName());
		if (map != null) {
			map.forEach((k, v) -> System.out.println(k + ":" + v));
		}
		else {
			System.out.println("找不到 Component 注解");
		}
	}

}

@Component
class MyMergedBeanDefinitionBeanPostProcessor implements MergedBeanDefinitionPostProcessor {


	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		//System.out.println(beanDefinition.getBeanClassName());
		//System.out.println(beanName);
		//System.out.println(beanType.getName());
	}

	@Override
	public void resetBeanDefinition(String beanName) {
		System.out.println(beanName);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
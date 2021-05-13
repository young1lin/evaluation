package me.young1lin.evaluation.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * get SpringApplicationContext use {@link ApplicationContextAware}by static method
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @version 1.0
 * @since 2020/11/16 10:06 上午
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

	/**
	 * static 对象在同一个 ClassLoader 下是唯一的，这里没有自定义 ClassLoader 去加载特定 Bean 所以可以保证获取
	 * ApplicationContext 是该容器下的
	 */
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringBeanUtil.applicationContext == null) {
			SpringBeanUtil.applicationContext = applicationContext;
		}
	}

	private static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}
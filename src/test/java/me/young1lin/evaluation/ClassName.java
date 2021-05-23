package me.young1lin.evaluation;

import java.beans.Introspector;

import org.junit.Test;

import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.ClassUtils;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/21 下午11:15
 * @version 1.0
 */
public class ClassName {

	/**
	 * but in the (unusual) special
	 * case when there is more than one character and both the first and
	 * second characters are upper case, we leave it alone.
	 * @see AnnotationBeanNameGenerator#buildDefaultBeanName
	 *
	 */
	@Test
	public void te() {
		String className = getClass().getName();
		String shortClassName = ClassUtils.getShortName(className);
		String beanName = Introspector.decapitalize(shortClassName);
		System.out.println(beanName);
		String url = "URL";
		url = Introspector.decapitalize(url);
		// 如果是两个大写字母，则不会首字母小写
		System.out.println(url);
	}

}

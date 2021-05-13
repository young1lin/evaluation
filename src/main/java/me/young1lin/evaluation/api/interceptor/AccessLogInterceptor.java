package me.young1lin.evaluation.api.interceptor;

import me.young1lin.evaluation.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * 生产上不需要这个，所以这个在对应的配置中是关闭的
 *
 * @author young1Lin
 */
@Slf4j
public class AccessLogInterceptor implements HandlerInterceptor {

	private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		START_TIME_THREAD_LOCAL.set(System.currentTimeMillis());
		if (handler instanceof HandlerMethod) {
			String date = DateUtil.getCurrentDateTime();
			HandlerMethod method = (HandlerMethod) handler;
			log.info("<=====================" + date + "=====================>");
			log.info("Controller: [{}]", method.getBean().getClass().getName());
			log.info("Method    : [{}]", method.getMethod().getName());
			log.info("Params    : [{}]", getParamString(request.getParameterMap()));
			log.info("URI       : [{}]", request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		Long endTime = System.currentTimeMillis();
		Long startTime = START_TIME_THREAD_LOCAL.get();
		long costTime = endTime - startTime;
		if (handler instanceof HandlerMethod) {
			String s = "<===========================" + costTime + "ms=============================>";
			log.info(s);
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex != null) {
			log.debug("Controller异常: " + getStackTraceAsString(ex));
		}
		START_TIME_THREAD_LOCAL.remove();
	}

	/**
	 * 将栈异常信息打印出来
	 *
	 * @param e Throwable
	 * @return java.lang.String
	 * @author 杨逸林
	 * @date 2019-08-15 23:44
	 */
	private String getStackTraceAsString(Throwable e) {
		if (e == null) {
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 将请求参数转换为String类型
	 *
	 * @param map requestMap
	 * @return requestMap as {@code String}
	 * @author 杨逸林
	 * @date 2019-08-15 23:44
	 */
	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		map.forEach((k, v) -> {
			sb.append(k)
					.append("=");
			if (v != null && v.length == 1) {
				sb.append(v[0]);
			}
			else {
				sb.append(Arrays.toString(v));
			}
			sb.append("\t");
		});
		return sb.toString();
	}

}

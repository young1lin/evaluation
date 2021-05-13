package me.young1lin.evaluation.api.holder;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import me.young1lin.evaluation.api.filter.InitServletHolder;

/**
 * 可以用 {@link RequestContextHolder} 代替
 * 我只是照着以前公司的技术总监代码抄的而已。这部分其实完全没必要，
 * 因为 Spring MVC 帮你做掉了，在 {@link org.springframework.web.servlet.FrameworkServlet}
 *
 * @author young1lin
 * @version 1.0
 * @date 2019/11/12 10:46 下午
 * @see InitServletHolder#doFilter
 */
public abstract class AbstractServletContextHolder implements Serializable {

	private static final String REQUEST_KEY = "request";

	private static final String RESPONSE_KEY = "response";

	private static final String SESSION_KEY = "session";

	private static final ThreadLocal<Map<String, Object>> CONTEXT_HOLDER_MAP = new NamedThreadLocal<>("servletContext");

	private static final long serialVersionUID = -116786107405567582L;


	public static void init(HttpServletRequest request, HttpServletResponse response) {
		CONTEXT_HOLDER_MAP.remove();
		Map<String, Object> contextMap = new HashMap<>(3);
		contextMap.put(SESSION_KEY, request.getSession());
		contextMap.put(REQUEST_KEY, request);
		contextMap.put(RESPONSE_KEY, response);
		CONTEXT_HOLDER_MAP.set(contextMap);
	}

	public static void clear() {
		CONTEXT_HOLDER_MAP.remove();
	}

	private static Map<String, Object> getContextHolderMap() {
		return CONTEXT_HOLDER_MAP.get();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getContextHolderMap().get(REQUEST_KEY);
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) getContextHolderMap().get(RESPONSE_KEY);
	}

	public static HttpSession getSession() {
		return (HttpSession) getContextHolderMap().get(SESSION_KEY);
	}

}

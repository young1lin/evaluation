package me.young1lin.evaluation.api.filter;

import me.young1lin.evaluation.api.holder.AbstractServletContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * @author young1lin
 * @version 1.0
 * @date 2019/11/12 10:59 下午
 */
@Slf4j
public class InitServletHolder implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		try {
			// 在 FrameworkServlet 中 RequestContextHolder 设置了值，可以在这里获取，不用额外写 filter 获取 Request 等信息。
			AbstractServletContextHolder.init(request, response);
			Objects.requireNonNull(AbstractServletContextHolder.getRequest(), "HttpRequest is null");
			filterChain.doFilter(request, response);
		}
		finally {
			AbstractServletContextHolder.clear();
		}
	}

}

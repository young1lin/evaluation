package me.young1lin.evaluation.api.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author young1Lin
 * 窗口过滤器,用于拦截指定ip访问
 * @date 2019/8/18 14:35
 */
@WebFilter(urlPatterns = "/d/*",filterName = "winFilter")
@Slf4j
public class WinFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteUrl = servletRequest.getRemoteAddr();
        log.info("RemoteURL : [{}]",remoteUrl);
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("WinFilter destroyed");
    }
}

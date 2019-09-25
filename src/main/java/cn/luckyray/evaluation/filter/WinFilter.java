package cn.luckyray.evaluation.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author young1Lin
 * @description 窗口过滤器,用于拦截指定ip访问
 * @date 2019/8/18 14:35
 * @github www.github.com/young1lin
 */
@WebFilter(urlPatterns = "/d/*",filterName = "winFilter")
@Slf4j
public class WinFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteURL = servletRequest.getRemoteAddr();
        log.info("RemoteURL : {}",remoteURL);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("WinFilter destroyed");
    }
}

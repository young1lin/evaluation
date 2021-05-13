package me.young1lin.evaluation.api.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * {@code org.springframework.core.annotation.Order} 注解 在这不起作用
 *
 * @author young1Lin
 */
@Slf4j
@WebFilter(urlPatterns = "/api/*")
public class ApiFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext sx = filterConfig.getServletContext();

        /*String contextName = filterConfig.getServletContext().getContextPath();
        log.info("上下文环境为：{}",contextName);
        Enumeration<String> names = filterConfig.getServletContext().getAttributeNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            log.info(name);
            Object o  = filterConfig.getServletContext().getAttribute(name);
            System.out.println(o);
        }*/
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();
        log.info("--------------costTime : [{}] ms", endTime - startTime);
    }

    @Override
    public void destroy() {
        log.info("APIFilter destroyed");
    }

}

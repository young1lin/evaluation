package cn.luckyray.evaluation.filter;

import cn.luckyray.evaluation.holder.AbstractServletContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
            AbstractServletContextHolder.init(request,response);
            Objects.requireNonNull(AbstractServletContextHolder.getRequest(),"HttpRequest is null");
            filterChain.doFilter(request,response);
        }finally {
            AbstractServletContextHolder.clear();
        }
    }
}

package cn.luckyray.evaluation.interceptor;

import cn.luckyray.evaluation.annotation.LogAnnotation;
import cn.luckyray.evaluation.service.AccessRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口访问日志打印并输入数据库
 * @author: young1Lin
 * @github www.github.com/young1lin
 */
@Slf4j
public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    private AccessRecordService accessRecordService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessIp = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        System.out.println(remoteHost);
       //                                                                                                                                                                               z                               z                       z                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            accessRecordService.insertAccessRecord(accessIp);
        log.info("访问地址为{}",accessIp);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
}

package cn.luckyray.evaluation.interceptor;

import cn.luckyray.evaluation.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: young1Lin
 * @GitHub www.github.com/young1lin
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTimeThreadLocal.set(System.currentTimeMillis());
        if(handler instanceof HandlerMethod){
            String date = DateUtil.getCurrentDate();
            HandlerMethod method = (HandlerMethod)handler;
            StringBuilder  sb = new StringBuilder(1000);
            sb.append("-----------------------")
                    .append(date)
                    .append("-------------------------------------\n")
                    .append("Controller: ")
                    .append(method.getBean().getClass().getName()).append("\n")
                    .append("Method    : ")
                    .append(method.getMethod().getName()).append("\n")
                    .append("Params    : ")
                    .append(getParamString(request.getParameterMap())).append("\n")
                    .append("URI       : ")
                    .append(request.getRequestURI());
            System.out.println(sb.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        Long endTime = System.currentTimeMillis();
        if(startTimeThreadLocal == null){
            log.error("startTimeThreadLocal has be collection");
            return;
        }
        Long startTime = startTimeThreadLocal.get();
        Long costTime = endTime-startTime;
        if(handler instanceof HandlerMethod){
            StringBuilder sb = new StringBuilder(1000);
            sb.append("----------------------------")
                    .append(costTime)
                    .append("ms")
                    .append("-------------------------------------");
            System.out.println(sb.toString());
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 打印JVM信息。
        //得到线程绑定的局部变量（开始时间）
        // long beginTime = startTimeThreadLocal.get();
        //2、结束时间
        //long endTime = System.currentTimeMillis();
        // 如果controller报错，则记录异常错误
        if(ex != null){
            log.debug("Controller异常: " + getStackTraceAsString(ex));
        }

        //log.info("计时结束：" + " 耗时：" + (endTime - beginTime) + " URI:" +
          //      request.getRequestURI()+ " 最大内存: " +Runtime.getRuntime().maxMemory()/1024/1024+ "m  已分配内存: " +Runtime.getRuntime().totalMemory()/1024/1024+ "m  已分配内存中的剩余空间: " +Runtime.getRuntime().freeMemory()/1024/1024+ "m  最大可用内存: " +
            //    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024 + "m");
        startTimeThreadLocal.remove();
    }
    /**
     * 将栈异常信息打印出来
     * @param: e
     * @author 杨逸林
     * @date 2019-08-15 23:44
     * @return java.lang.String
    */
    private String getStackTraceAsString(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 将请求参数转换为String类型
     * @param: map
     * @author 杨逸林
     * @date 2019-08-15 23:44
     * @return java.lang.String
    */
    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((k,v)->{
            sb.append(k)
              .append("=");
            if(v != null && v.length==1){
                sb.append(v[0])
                        .append("\t");
            }else{
                sb.append(Arrays.toString(v))
                        .append("\t");
            }
        });
        return sb.toString();
    }
}

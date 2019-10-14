package cn.luckyray.evaluation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类，待完善
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019-07-08 15:24
 **/
public class DateUtil {

    /**
     * 以JDK1.8线程安全的方式获得当前格式化好的日期
     * @author 杨逸林
     * @date 2019-07-09 15:28
     * @return java.lang.String
    */
    public static String getCurrentDate(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(dtf);
    }

    public static String getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(dtf);
    }
}

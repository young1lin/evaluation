package cn.luckyray.evaluation;


import cn.luckyray.evaluation.util.DateUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/4 14:56
 **/

public class TestMemcache {
    @Test
    public void pp(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.toString());
        System.out.println(now.format(dtf));
    }
}

package cn.luckyray.evaluation.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *  index页面
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 8:39
 **/
@Controller
public class IndexController {

    @RequestMapping("/d")
    public ModelAndView index(String u){
        ModelAndView modelAndView = new ModelAndView();
        if(StringUtils.isBlank(u)){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("winNum",u);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
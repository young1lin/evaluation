package cn.luckyray.evaluation.api;

import cn.luckyray.evaluation.constant.ResultCode;
import cn.luckyray.evaluation.entity.Active;
import cn.luckyray.evaluation.entity.ApiReturnObject;
import cn.luckyray.evaluation.exception.RuleException;
import cn.luckyray.evaluation.service.UserService;
import cn.luckyray.evaluation.util.ApiReturnUtil;
import cn.luckyray.evaluation.vo.CallEvaluationVO;
import cn.luckyray.evaluation.vo.UserVO;
import cn.luckyray.evaluation.websocket.EvaluationServer;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  用于 API 调用
 * 调用评价器的 api 接口
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 9:34
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class CallEvaluationController {

    /**
     * 开始评价接口
     * @author 杨逸林
     * @date 2019/7/4 13:50
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @RequestMapping("/startEvaluate")
    public ApiReturnObject startEvaluate(@Valid CallEvaluationVO callEvaluationVO){
        Integer userId = callEvaluationVO.getUserId();
        ConcurrentHashMap<String, EvaluationServer> map = EvaluationServer.getWebSocketList();
        if(map.get(callEvaluationVO.getWinNum()) == null){ return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM); }
        Map<String,Object> data = new HashMap(4);
        data.put("active","startEvaluate");
        data.put("userId",callEvaluationVO.getUserId());
        data.put("serialNum",callEvaluationVO.getSerialNum());
        String message = JSON.toJSONString(ApiReturnUtil.success(data));
        try {
            EvaluationServer.sendInfo(message,callEvaluationVO.getWinNum());
        } catch (RuleException e) {
            e.printStackTrace();
            log.error("[{}]窗口不存在，或者客户端已断开，detail is [{}]",callEvaluationVO.getWinNum(),e.getMessage());
            return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM);
        }
        return ApiReturnUtil.success();
    }


    /**
     * 员工登录接口
     * @param userVO
     * @param winNum
     * @author 杨逸林
     * @date 2019-09-25 21:43
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @RequestMapping("/login")
    public ApiReturnObject login(UserVO userVO,String winNum){
        Active active = new Active("login",userVO);
        EvaluationServer.sendInfoToOCX(active,winNum);
        return ApiReturnUtil.success();
    }
}

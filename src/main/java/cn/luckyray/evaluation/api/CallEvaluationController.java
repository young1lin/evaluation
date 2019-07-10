package cn.luckyray.evaluation.api;

import cn.luckyray.evaluation.constant.ResultCode;
import cn.luckyray.evaluation.entity.ApiReturnObject;
import cn.luckyray.evaluation.service.UserService;
import cn.luckyray.evaluation.util.ApiReturnUtil;
import cn.luckyray.evaluation.websocket.EvaluationServer;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;

    /**
     * 开始评价接口
     * @param winNum
     * @param userId
     * @author 杨逸林
     * @date 2019/7/4 13:50
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @RequestMapping("/startEvaluate")
    public ApiReturnObject startEvaluate(String winNum,Integer userId,String serialNum){
        // 验证窗口是否为空
        if(StringUtils.isBlank(winNum)){ return ApiReturnUtil.failure(ResultCode.WINDOW_NUM_IS_EMPTY); }
        // 验证流水号是否为空
        ConcurrentHashMap<String, EvaluationServer> map = EvaluationServer.getWebSocketList();
        if(map.get(winNum) == null){ return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM); }
        Map<String,Object> data = new HashMap(8);
        data.put("active","startEvaluate");
        data.put("userId",userId);
        data.put("serialNum",serialNum);
        String message = JSON.toJSONString(ApiReturnUtil.success(data));
        try {
            EvaluationServer.sendInfo(message,winNum);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("{}窗口不存在，或者客户端已断开",winNum);
            return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM);
        }
        return ApiReturnUtil.success();
    }
}

package cn.luckyray.evaluation.api;

import cn.luckyray.evaluation.constant.ResultCode;
import cn.luckyray.evaluation.entity.ApiReturnObject;
import cn.luckyray.evaluation.exception.RuleException;
import cn.luckyray.evaluation.holder.AbstractServletContextHolder;
import cn.luckyray.evaluation.service.UserService;
import cn.luckyray.evaluation.util.ApiReturnUtil;
import cn.luckyray.evaluation.vo.CallEvaluationVO;
import cn.luckyray.evaluation.websocket.EvaluationServer;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
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
     * @author 杨逸林
     * @date 2019/7/4 13:50
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @ApiOperation(value = "对指定webSocket客户端发起评价")
    @ApiResponses({
            @ApiResponse(code=200,message="成功",response=ApiReturnObject.class),
    })
    @GetMapping("/startEvaluate")
    public ApiReturnObject startEvaluate(@Valid CallEvaluationVO callEvaluationVO){
        Integer userId = callEvaluationVO.getUserId();
        ConcurrentHashMap<String, EvaluationServer> map = EvaluationServer.getWebSocketList();
        if(map.get(callEvaluationVO.getWinNum()) == null){ return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM); }
        Map<String,Object> data = new HashMap<>(4);
        data.put("active","startEvaluate");
        data.put("userId",callEvaluationVO.getUserId());
        data.put("serialNum",callEvaluationVO.getSerialNum());
        String message = JSON.toJSONString(ApiReturnUtil.success(data));
        try {
            EvaluationServer.sendInfo(message,callEvaluationVO.getWinNum());
        } catch (RuleException e) {
            e.printStackTrace();

            if(log.isDebugEnabled()){
                log.debug("[{}]窗口不存在，或者客户端已断开，detail is [{}]",callEvaluationVO.getWinNum(),e.getMessage());
            }
            return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM);
        }
        return ApiReturnUtil.success();
    }

    @GetMapping("/end")
    public ApiReturnObject end(){
        HttpServletRequest request = AbstractServletContextHolder.getRequest();
        Map<String,String[]> paramMap = request.getParameterMap();
        paramMap.forEach((k,v)->{
            StringBuilder sb = new StringBuilder(k);
            if(v.length==1){
                sb.append(v[0]);
            }else{
                sb.append(Arrays.toString(v));
            }
            System.out.println(sb.toString());
        });
        userService.userIsExist(2);
        return ApiReturnUtil.success();
    }
}

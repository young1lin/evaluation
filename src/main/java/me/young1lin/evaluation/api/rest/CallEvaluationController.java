package me.young1lin.evaluation.api.rest;

import me.young1lin.evaluation.common.constant.ResultCode;
import me.young1lin.evaluation.api.domain.ApiReturnObject;
import me.young1lin.evaluation.common.exception.WindowIsNotFoundException;
import me.young1lin.evaluation.api.holder.AbstractServletContextHolder;
import me.young1lin.evaluation.api.domain.sysuser.service.UserService;
import me.young1lin.evaluation.common.util.ApiReturnUtil;
import me.young1lin.evaluation.api.vo.CallEvaluationVO;
import me.young1lin.evaluation.websocket.server.EvaluationServer;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
 * 用于 API 调用
 * 调用评价器的 api 接口
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 9:34
 **/
@Api("发起评价接口")
@ApiResponses(
        value = {
                @ApiResponse(code = 200, message = "请求成功", response = ApiReturnObject.class),
                @ApiResponse(code = 404, message = "请求资源不存在", response = ApiReturnObject.class),
                @ApiResponse(code = 408, message = "请求超时", response = ApiReturnObject.class),
                @ApiResponse(code = 429, message = "请求达到上限", response = ApiReturnObject.class),
                @ApiResponse(code = 500, message = "请求出错", response = ApiReturnObject.class)
        }
)
@RestController
@RequestMapping("/api")
@Slf4j
public class CallEvaluationController {

    private final UserService userService;

    public CallEvaluationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 开始评价接口
     *
     * @return me.young1lin.evaluation.api.domain.ApiReturnObject
     * @author 杨逸林
     * @date 2019/7/4 13:50
     */
    @ApiOperation(value = "对指定webSocket客户端发起评价")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Content-Type", value = "请求头", required = true,
                    dataType = "String", defaultValue = MediaType.APPLICATION_JSON_VALUE)
    })
    @GetMapping("/startEvaluate")
    public ApiReturnObject startEvaluate(@Valid CallEvaluationVO callEvaluationVO) throws WindowIsNotFoundException {
        Integer userId = callEvaluationVO.getUserId();
        boolean userExists = userService.userIsExist(userId);
        ConcurrentHashMap<String, EvaluationServer> map = EvaluationServer.getWebSocketList();
        if (map.get(callEvaluationVO.getWinNum()) == null) {
            return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM);
        }
        Map<String, Object> data = new HashMap<>(5);
        data.put("active", "startEvaluate");
        data.put("userId", callEvaluationVO.getUserId());
        data.put("serialNum", callEvaluationVO.getSerialNum());
        String message = JSON.toJSONString(ApiReturnUtil.success(data));
        EvaluationServer.sendInfo(message, callEvaluationVO.getWinNum());
        return ApiReturnUtil.success();
    }

    @GetMapping("/end")
    public ApiReturnObject end() {
        HttpServletRequest request = AbstractServletContextHolder.getRequest();
        Map<String, String[]> paramMap = request.getParameterMap();
        paramMap.forEach((k, v) -> {
            StringBuilder sb = new StringBuilder(k);
            if (v.length == 1) {
                sb.append(v[0]);
            } else {
                sb.append(Arrays.toString(v));
            }
            System.out.println(sb.toString());
        });
        userService.userIsExist(2);
        return ApiReturnUtil.success();
    }

}

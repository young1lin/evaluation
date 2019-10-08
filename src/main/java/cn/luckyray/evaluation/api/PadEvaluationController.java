package cn.luckyray.evaluation.api;


import cn.luckyray.evaluation.entity.Active;
import cn.luckyray.evaluation.entity.ApiReturnObject;
import cn.luckyray.evaluation.entity.BaseEntity;
import cn.luckyray.evaluation.util.ApiReturnUtil;
import cn.luckyray.evaluation.vo.BaseEntityVO;
import cn.luckyray.evaluation.vo.EvaluateVO;
import cn.luckyray.evaluation.vo.UserVO;
import cn.luckyray.evaluation.websocket.EvaluationServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author
 * 评价器控制接口
 */
@RestController
@RequestMapping("/padApi")
public class PadEvaluationController {

    private static final String DEFAULT_PAGE_NUM = "1";

    @RequestMapping(value = "/{active}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiReturnObject active(@PathVariable("active") String active, @RequestParam Map<String,String> param){
        EvaluationServer.sendInfoToOCX(new Active(active,param),DEFAULT_PAGE_NUM);
        return ApiReturnUtil.success();
    }
}

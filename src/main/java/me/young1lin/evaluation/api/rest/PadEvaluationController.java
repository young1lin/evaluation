package me.young1lin.evaluation.api.rest;


import me.young1lin.evaluation.api.domain.Active;
import me.young1lin.evaluation.api.domain.ApiReturnObject;
import me.young1lin.evaluation.common.util.ApiReturnUtil;
import me.young1lin.evaluation.websocket.server.EvaluationServer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author
 * 评价器控制接口
 */
@RestController
@RequestMapping("/padApi")
public class PadEvaluationController {

	private static final String DEFAULT_PAGE_NUM = "1";

	/**
	 * 向OCX发送指令，并且解决跨域问题，加上produces
	 * @param active 互动类型
	 * @param param 互动参数
	 * @return 成功
	 */
	@GetMapping(value = "/{active}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiReturnObject active(@PathVariable("active") String active, @RequestParam Map<String, Object> param) {
		EvaluationServer.sendInfoToOCX(new Active(active, param), DEFAULT_PAGE_NUM);
		return ApiReturnUtil.success();
	}

}

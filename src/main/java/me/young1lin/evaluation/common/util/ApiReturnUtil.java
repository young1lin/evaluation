package me.young1lin.evaluation.common.util;

import me.young1lin.evaluation.common.constant.ResultCode;
import me.young1lin.evaluation.api.domain.ApiReturnObject;

/**
 * API调用返回信息工具类
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 10:26
 **/

public class ApiReturnUtil {

	/**
	 *  无参返回成功
	 * @author 杨逸林
	 * @date 2019/7/3 10:46
	 * @return cn.luckyray.evaluation.entity.ApiReturnObject
	 */
	public static ApiReturnObject success() {
		ApiReturnObject apiReturnObject = new ApiReturnObject();
		apiReturnObject.setResultCode(ResultCode.SUCCESS);
		return apiReturnObject;
	}

	/**
	 *  返回api所需参数
	 * @param data 数据
	 * @author 杨逸林
	 * @date 2019/7/3 10:45
	 * @return cn.luckyray.evaluation.entity.ApiReturnObject
	 */
	public static ApiReturnObject success(Object data) {
		ApiReturnObject apiReturnObject = new ApiReturnObject();
		apiReturnObject.setResultCode(ResultCode.SUCCESS);
		apiReturnObject.setData(data);
		return apiReturnObject;
	}

	/**
	 * 返回失败状态码及信息
	 * @author 杨逸林
	 * @date 2019/7/3 10:47
	 * @return cn.luckyray.evaluation.entity.ApiReturnObject
	 */
	public static ApiReturnObject failure(ResultCode resultCode) {
		ApiReturnObject apiReturnObject = new ApiReturnObject();
		apiReturnObject.setResultCode(resultCode);
		return apiReturnObject;
	}

	/**
	 * 有参，有状态码及失败信息
	 * @param resultCode 状态码
	 * @param data 数据
	 * @author 杨逸林
	 * @date 2019/7/3 10:52
	 * @return cn.luckyray.evaluation.entity.ApiReturnObject
	 */
	public static ApiReturnObject failure(ResultCode resultCode, Object data) {
		ApiReturnObject apiReturnObject = new ApiReturnObject();
		apiReturnObject.setResultCode(resultCode);
		apiReturnObject.setData(data);
		return apiReturnObject;
	}
}

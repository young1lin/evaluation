package me.young1lin.evaluation.api.exceptionhandler;

import me.young1lin.evaluation.common.constant.ResultCode;
import me.young1lin.evaluation.api.domain.ApiReturnObject;
import me.young1lin.evaluation.common.exception.WindowIsNotFoundException;
import me.young1lin.evaluation.common.util.ApiReturnUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常捕获
 *
 * @author 杨逸林
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 表单验证时异常返回信息
	 *
	 * @param bindException 绑定异常
	 * @return ResponseEntity
	 * @author 杨逸林
	 * @date 2019-07-29 22:27
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> defaultExceptionHandler(BindException bindException) {
		//处理返回的错误信息
		List<ObjectError> errors = bindException.getBindingResult().getAllErrors();
		if (!CollectionUtils.isEmpty(errors)) {
			ObjectError objectError = errors.get(0);
			ResultCode resultCode = ResultCode.FAILURE.setMessage(objectError.getDefaultMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultCode);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 全局异常捕获
	 *
	 * @param ex exception
	 * @return ApiReturnObject
	 * @author 杨逸林
	 * @date 2019-07-29 22:29
	 */
	@ExceptionHandler(Exception.class)
	public ApiReturnObject defaultExceptionHandler(Exception ex) {
		ex.printStackTrace();
		return ApiReturnUtil.failure(ResultCode.SYSTEM_EXCEPTION);
	}

	@ExceptionHandler(WindowIsNotFoundException.class)
	public ApiReturnObject defaultExceptionHandler(WindowIsNotFoundException windowIsNotFoundException) {
		windowIsNotFoundException.getMessage();
		windowIsNotFoundException.printStackTrace();
		return ApiReturnUtil.failure(ResultCode.NOT_EXIST_WINDOW_NUM);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> defaultExceptionHandler(RuntimeException runtimeException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runtimeException);
	}

}

package cn.luckyray.evaluation.handler;

import cn.luckyray.evaluation.constant.ResultCode;
import cn.luckyray.evaluation.entity.ApiReturnObject;
import cn.luckyray.evaluation.util.ApiReturnUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常捕获
 * @author 杨逸林
*/
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {
    /**
     * 表单验证时异常返回信息
     * @param bindException
     * @author 杨逸林
     * @date 2019-07-29 22:27
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ApiReturnObject defaultExceptionHandler(BindException bindException) {
        //处理返回的错误信息
        List<ObjectError> errors = bindException.getBindingResult().getAllErrors();
        if(errors.size()>0){
            ObjectError objectError = errors.get(0);
            ResultCode resultCode = ResultCode.FAILURE.setMessage(objectError.getDefaultMessage());
            return ApiReturnUtil.failure(resultCode);
        }
        return ApiReturnUtil.failure(ResultCode.SYSTEM_EXCEPTION);
    }

    /**
     * 全局异常捕获
     * @param ex
     * @author 杨逸林
     * @date 2019-07-29 22:29
     * @return cn.luckyray.evaluation.entity.ApiReturnObject
    */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiReturnObject defaultExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ApiReturnUtil.failure(ResultCode.SYSTEM_EXCEPTION);
    }
}

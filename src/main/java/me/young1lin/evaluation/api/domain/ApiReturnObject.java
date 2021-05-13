package me.young1lin.evaluation.api.domain;

import me.young1lin.evaluation.common.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于返回 API 信息
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 10:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiReturnObject {

    private Integer code;

    private String message;

    private Object data;

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

}

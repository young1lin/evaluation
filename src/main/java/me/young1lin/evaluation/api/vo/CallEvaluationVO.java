package me.young1lin.evaluation.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 叫号结束后调用类
 * @author 杨逸林
*/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class CallEvaluationVO implements Serializable {

    private static final long serialVersionUID = -1858444144575701063L;

    @ApiParam(name = "winNum",value = "窗口号",required = true,example = "1")
    @NotBlank(message = "窗口号不能为空")
    private String winNum;

    @ApiParam(name = "userId",value = "用户ID",required = true,example = "1")
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @ApiParam(name = "serialNum",value = "流水号",required = true,example = "123414322")
    @NotBlank(message = "流水号不能为空")
    @Size(max = 22,min = 8,message = "流水号长度应为8-22")
    private String serialNum;
    
}

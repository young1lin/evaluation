package cn.luckyray.evaluation.vo;

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
public class CallEvaluationVO implements Serializable {
    @NotBlank(message = "窗口号不能为空")
    private String winNum;
    @NotNull(message = "用户ID不能为空")
    private Integer userId;
    @NotBlank(message = "流水号不能为空")
    @Size(max = 22,min = 8,message = "流水号长度应为8-22")
    private String serialNum;
}

package cn.luckyray.evaluation.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author young1Lin
 * @description
 * @date 2019/9/26 9:57
 * @github www.github.com/young1lin
 */
@Data
@ToString(callSuper = true)
public class EvaluateVO extends BaseEntityVO {
    String lsh;
}

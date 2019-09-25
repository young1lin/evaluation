package cn.luckyray.evaluation.entity;

import cn.luckyray.evaluation.vo.BaseEntityVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author young1Lin
 * @description
 * @date 2019/9/25 21:12
 * @github www.github.com/young1lin
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Active<T extends BaseEntityVO>{
    private String active;
    private T activeData;
}

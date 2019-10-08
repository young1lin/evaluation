package cn.luckyray.evaluation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

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
public class Active{
    private String active;
    private Map activeData;
}

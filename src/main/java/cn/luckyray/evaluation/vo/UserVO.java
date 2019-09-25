package cn.luckyray.evaluation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author young1Lin
 * @description
 * @date 2019/9/25 20:52
 * @github www.github.com/young1lin
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String ip;
    private String winNum;
    private String name;
    private String userNo;
    private String winType;
}

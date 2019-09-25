package cn.luckyray.evaluation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/4 15:41
 **/

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    private String username;
}

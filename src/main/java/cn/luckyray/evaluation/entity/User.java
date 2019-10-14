package cn.luckyray.evaluation.entity;

import lombok.*;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/4 15:41
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    private String username;
}

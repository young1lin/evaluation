package me.young1lin.evaluation.api.domain.sysuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import me.young1lin.evaluation.api.domain.BaseEntity;

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
@TableName("User")
public class User extends BaseEntity {

    private String username;

}

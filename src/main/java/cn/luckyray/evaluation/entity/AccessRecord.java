package cn.luckyray.evaluation.entity;

import lombok.*;


/**
 * @author young1Lin
 * @github www.github.com/young1lin
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccessRecord extends BaseEntity{
    private Long accessIpAddress;
}

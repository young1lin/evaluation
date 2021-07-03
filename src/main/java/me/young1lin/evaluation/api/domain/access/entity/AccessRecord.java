package me.young1lin.evaluation.api.domain.access.entity;

import lombok.*;
import me.young1lin.evaluation.api.domain.BaseEntity;


/**
 * @author young1Lin
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccessRecord extends BaseEntity {

	private Long accessIpAddress;

}

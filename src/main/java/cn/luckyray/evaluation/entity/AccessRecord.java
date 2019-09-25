package cn.luckyray.evaluation.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: young1Lin
 * @github www.github.com/young1lin
 */
@Entity
@Table(name="access_record")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccessRecord extends BaseEntity{
    @Column(name = "access_ip_address")
    private Long accessIpAddress;
}

package cn.luckyray.evaluation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author young1Lin
 * @github www.github.com/young1lin
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "deleted")
    private char deleted = 0;
    @Column(updatable = false,name = "creator")
    private String creator;
    @Column(name = "updater")
    private String updater;
    @Column(updatable = false,name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;

}

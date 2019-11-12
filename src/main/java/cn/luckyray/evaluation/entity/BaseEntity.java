package cn.luckyray.evaluation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author young1Lin
 * @github www.github.com/young1lin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    protected int id;
    protected char deleted = 0;
    protected String creator;
    protected String updater;
    protected String createTime;
    protected String updateTime;

}

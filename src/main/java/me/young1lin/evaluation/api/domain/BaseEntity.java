package me.young1lin.evaluation.api.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author young1Lin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

	@TableId
	protected Long id;

	@TableField("deleted")
	protected boolean deleted = Boolean.FALSE;

	@TableField("creator")
	protected String creator;

	@TableField("updater")
	protected String updater;

	@TableField("create_time")
	protected String createTime;

	@TableField("update_time")
	protected String updateTime;

}

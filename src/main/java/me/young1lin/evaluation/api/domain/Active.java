package me.young1lin.evaluation.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author young1Lin
 * @date 2019/9/25 21:12
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Active {

	private String active;

	private Map<String, Object> activeData;

}

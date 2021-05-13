package me.young1lin.evaluation.api.domain.access.service;

import me.young1lin.evaluation.api.domain.access.repository.AccessRecordMapper;
import me.young1lin.evaluation.api.domain.access.entity.AccessRecord;
import me.young1lin.evaluation.common.util.DateUtil;
import me.young1lin.evaluation.common.util.IPUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author young1Lin
 * @date 2019/8/18 17:55
 */
@Service
public class AccessRecordService {

	@Autowired
	private AccessRecordMapper accessRecordMapper;


	public boolean selectCountAccessIp(String ip) {
		int count = accessRecordMapper.selectCountAccessIp(IPUtil.ip2Long(ip));
		return count != 0;

	}

	public void insertAccessRecord(String ip) {
		AccessRecord accessRecord = new AccessRecord();
		Long remoteAddress = IPUtil.ip2Long(ip);
		String currentDate = DateUtil.getCurrentDateTime();
		accessRecord.setAccessIpAddress(remoteAddress);
		accessRecord.setCreator(ip);
		accessRecord.setCreator(ip);
		accessRecord.setDeleted(false);
		accessRecord.setCreateTime(currentDate);
		accessRecord.setUpdateTime(currentDate);
		accessRecordMapper.insert(accessRecord);
	}

}

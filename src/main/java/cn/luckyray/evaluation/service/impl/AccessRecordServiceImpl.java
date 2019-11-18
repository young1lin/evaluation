package cn.luckyray.evaluation.service.impl;

import cn.luckyray.evaluation.dao.main.AccessRecordMapper;
import cn.luckyray.evaluation.entity.AccessRecord;
import cn.luckyray.evaluation.service.AccessRecordService;
import cn.luckyray.evaluation.util.DateUtil;
import cn.luckyray.evaluation.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author young1Lin
 * @date 2019/8/18 17:55
 * @github www.github.com/young1lin
 */
@Service
public class AccessRecordServiceImpl implements AccessRecordService {

    @Autowired
    private AccessRecordMapper accessRecordMapper;

    @Override
    public boolean selectCountAccessIp(String ip) {
        int count = accessRecordMapper.selectCountAccessIp(IPUtil.ip2Long(ip));
        return count != 0;
    }

    @Override
    public void insertAccessRecord(String ip){
        AccessRecord accessRecord = new AccessRecord();
        Long remoteAddress = IPUtil.ip2Long(ip);
        String currentDate = DateUtil.getCurrentDateTime();
        accessRecord.setAccessIpAddress(remoteAddress);
        accessRecord.setCreator(ip);
        accessRecord.setCreator(ip);
        accessRecord.setDeleted('0');
        accessRecord.setCreateTime(currentDate);
        accessRecord.setUpdateTime(currentDate);
        accessRecordMapper.insert(accessRecord);
    }
}

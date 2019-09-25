package cn.luckyray.evaluation.service;


/**
 * @author young1Lin
 * @description
 * @date 2019/8/18 17:53
 * @github www.github.com/young1lin
 */
public interface AccessRecordService {
    boolean selectCountAccessIp(String ip);
    void insertAccessRecord(String ip);
}

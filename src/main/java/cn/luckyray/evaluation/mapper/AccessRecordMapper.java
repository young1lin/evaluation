package cn.luckyray.evaluation.mapper;

import cn.luckyray.evaluation.entity.AccessRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author young1Lin
 * @date 2019/8/18 14:09
 * @github www.github.com/young1lin
 */
@Repository
public interface AccessRecordMapper extends BaseMapper<AccessRecord> {
    /**
     * 根据ip统计是否存在
     * @param: ip
     * @date 2019-08-18 18:07
     * @return int
    */
    int selectCountAccessIp(@Param("ip") Long ip);
}
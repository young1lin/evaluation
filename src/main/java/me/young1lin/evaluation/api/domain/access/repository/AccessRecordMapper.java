package me.young1lin.evaluation.api.domain.access.repository;

import me.young1lin.evaluation.api.domain.access.entity.AccessRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author young1Lin
 * @date 2019/8/18 14:09
 */
@Repository
@Mapper
public interface AccessRecordMapper extends BaseMapper<AccessRecord> {
    /**
     * 根据ip统计是否存在
     * @param ip access IP
     * @date 2019-08-18 18:07
     * @return is exits
    */
    int selectCountAccessIp(@Param("ip") Long ip);

}
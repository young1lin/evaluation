package me.young1lin.evaluation.api.domain.sysuser.repository;

import me.young1lin.evaluation.api.domain.sysuser.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019-07-08 8:46
 **/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 统计用户数量
     * @param id userId
     * @author 杨逸林
     * @date 2019-07-08 8:51
     * @return int
    */
    int countUserById(Integer id);
}

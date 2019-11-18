package cn.luckyray.evaluation.dao.main;

import cn.luckyray.evaluation.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019-07-08 8:46
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 统计用户数量
     * @param id
     * @author 杨逸林
     * @date 2019-07-08 8:51
     * @return int
    */
    int countUserById(Integer id);
}

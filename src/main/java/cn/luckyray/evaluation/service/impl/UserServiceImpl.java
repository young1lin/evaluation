package cn.luckyray.evaluation.service.impl;

import cn.luckyray.evaluation.mapper.UserMapper;
import cn.luckyray.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * TODO
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/4 15:53
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean userIsExist(int id){
        int userCount = userMapper.countUserById(id);
        return userCount > 0 ? true:false;
    }

}

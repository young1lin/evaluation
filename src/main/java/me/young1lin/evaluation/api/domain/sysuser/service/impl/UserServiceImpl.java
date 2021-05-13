package me.young1lin.evaluation.api.domain.sysuser.service.impl;

import me.young1lin.evaluation.api.domain.sysuser.service.UserService;
import me.young1lin.evaluation.api.domain.sysuser.repository.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/4 15:53
 **/
@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Autowired
	private void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public boolean userIsExist(int id) {
		return userMapper.countUserById(id) != 0;
	}

}

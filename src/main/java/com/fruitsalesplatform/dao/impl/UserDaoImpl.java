package com.fruitsalesplatform.dao.impl;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatfrom.dao.UserDao;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
		super.setNameSpace("com.fruitsalesplatform.mapper.UserMapper");
	}

	@Override
	public User getUserFromQQOpenId(String qqOpenId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".getUserFromQQOpenId", qqOpenId);
	}

	@Override
	public User getUserFromUserName(String userName) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".getUserFromUserName", userName);
	}
}

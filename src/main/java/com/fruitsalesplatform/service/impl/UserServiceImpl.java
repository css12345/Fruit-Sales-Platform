package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;
import com.fruitsalesplatfrom.dao.UserDao;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User get(Serializable id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public List<User> find(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return userDao.find(map);
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		userDao.insert(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		userDao.delete(ids);
	}

	@Override
	public User getUserFromQQOpenId(String qqOpenId) {
		// TODO Auto-generated method stub
		return userDao.getUserFromQQOpenId(qqOpenId);
	}

	@Override
	public User getUserFromUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserFromUserName(userName);
	}

}

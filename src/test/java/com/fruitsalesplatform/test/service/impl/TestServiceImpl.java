package com.fruitsalesplatform.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.test.dao.TestDao;
import com.fruitsalesplatform.test.entity.User;
import com.fruitsalesplatform.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	
	
	@Override
	public List<User> findUserByName(User user) {
		// TODO Auto-generated method stub
		return testDao.findUserByName(user);
	}

}

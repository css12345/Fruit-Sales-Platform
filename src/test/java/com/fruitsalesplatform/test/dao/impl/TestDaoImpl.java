package com.fruitsalesplatform.test.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.test.dao.TestDao;
import com.fruitsalesplatform.test.entity.User;

@Repository
public class TestDaoImpl implements TestDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession = null;
	
	private SqlSession getSqlSession() {
		if(sqlSession == null)
			sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	@Override
	public List<User> findUserByName(User user) {
		// TODO Auto-generated method stub
		List<User> list = getSqlSession().selectList("test.findUserByName", "%" + user.getName() + "%");
		return list;
	}
}

package com.fruitsalesplatform.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitsalesplatfrom.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	private String nameSpace;

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(nameSpace + ".get", id);
	}

	@Override
	public List<T> find(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		List<T> list = this.getSqlSession().selectList(nameSpace + ".find", map);
		return list;
	}

	@Override
	public void insert(T entity) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(nameSpace + ".insert", entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(nameSpace + ".update", entity);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(nameSpace + ".deleteById", id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(nameSpace + ".delete", ids);
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

}

package com.fruitsalesplatform.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatfrom.dao.RetailerDao;

@Repository
public class RetailerDaoImpl extends BaseDaoImpl<Retailer> implements RetailerDao {
	public RetailerDaoImpl() {
		// TODO Auto-generated constructor stub
		super.setNameSpace("com.fruitsalesplatform.mapper.RetailerMapper");
	}
	
	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".count", map);
	}

}

package com.fruitsalesplatform.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatfrom.dao.CommoditiesDao;
@Repository
public class CommoditiesDaoImpl extends BaseDaoImpl<Commodities> implements CommoditiesDao {
	
	public CommoditiesDaoImpl() {
		// TODO Auto-generated constructor stub
		this.setNameSpace("com.fruitsalesplatform.mapper.CommoditiesMapper");
	}
	
	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".count", map);
	}

}

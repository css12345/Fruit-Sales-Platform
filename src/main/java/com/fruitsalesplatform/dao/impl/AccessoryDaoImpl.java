package com.fruitsalesplatform.dao.impl;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatfrom.dao.AccessoryDao;
@Repository
public class AccessoryDaoImpl extends BaseDaoImpl<Accessory> implements AccessoryDao {
	public AccessoryDaoImpl() {
		this.setNameSpace("com.fruitsalesplatform.mapper.AccessoryMapper");
	}

	@Override
	public int deleteByFruitId(String fruitId) {
		// TODO Auto-generated method stub	
		return this.getSqlSession().delete(this.getNameSpace() + ".deleteByFruitId", fruitId);
	}
}

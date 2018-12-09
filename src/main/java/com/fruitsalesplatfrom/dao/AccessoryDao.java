package com.fruitsalesplatfrom.dao;

import com.fruitsalesplatform.entity.Accessory;

public interface AccessoryDao extends BaseDao<Accessory> {
	public int deleteByFruitId(String fruitId);
}

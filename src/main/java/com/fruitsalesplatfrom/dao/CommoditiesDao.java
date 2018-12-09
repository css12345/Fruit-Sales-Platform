package com.fruitsalesplatfrom.dao;

import java.util.Map;

import com.fruitsalesplatform.entity.Commodities;

public interface CommoditiesDao extends BaseDao<Commodities>{
	public int count(@SuppressWarnings("rawtypes") Map map);
}

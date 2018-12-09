package com.fruitsalesplatfrom.dao;

import java.util.Map;

import com.fruitsalesplatform.entity.Retailer;

public interface RetailerDao extends BaseDao<Retailer> {
	public int count(@SuppressWarnings("rawtypes") Map map);
}

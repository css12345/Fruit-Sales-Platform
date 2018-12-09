package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.CommoditiesService;
import com.fruitsalesplatfrom.dao.CommoditiesDao;

@Service
public class CommoditiesServiceImpl implements CommoditiesService {
	@Autowired
	private CommoditiesDao commoditiesDao;
	
	@Override
	public Commodities get(Serializable id) {
		// TODO Auto-generated method stub
		return commoditiesDao.get(id);
	}

	@Override
	public List<Commodities> find(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return commoditiesDao.find(map);
	}

	@Override
	public void insert(Commodities commodities) {
		// TODO Auto-generated method stub
		commoditiesDao.insert(commodities);
	}

	@Override
	public void update(Commodities commodities) {
		// TODO Auto-generated method stub
		commoditiesDao.update(commodities);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		commoditiesDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		commoditiesDao.delete(ids);
	}

	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return commoditiesDao.count(map);
	}

}

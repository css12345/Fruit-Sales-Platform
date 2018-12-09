package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;
import com.fruitsalesplatfrom.dao.RetailerDao;

@Service
public class RetailerServiceImpl implements RetailerService {
	@Autowired
	private RetailerDao retailerDao;
	
	@Override
	public Retailer get(Serializable id) {
		// TODO Auto-generated method stub
		return retailerDao.get(id);
	}

	@Override
	public List<Retailer> find(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return retailerDao.find(map);
	}

	@Override
	public void insert(Retailer retailer) {
		// TODO Auto-generated method stub
		retailerDao.insert(retailer);
	}

	@Override
	public void update(Retailer retailer) {
		// TODO Auto-generated method stub
		retailerDao.update(retailer);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		retailerDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		retailerDao.delete(ids);
	}

	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return retailerDao.count(map);
	}

}

package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;
import com.fruitsalesplatfrom.dao.AccessoryDao;
@Service
public class AccessoryServiceImpl implements AccessoryService {
	@Autowired
	private AccessoryDao accessoryDao;
	@Override
	public Accessory get(Serializable id) {
		// TODO Auto-generated method stub
		return accessoryDao.get(id);
	}

	@Override
	public List<Accessory> find(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return accessoryDao.find(map);
	}

	@Override
	public void insert(Accessory accessory) {
		// TODO Auto-generated method stub
		accessoryDao.insert(accessory);
	}

	@Override
	public void update(Accessory accessory) {
		// TODO Auto-generated method stub
		accessoryDao.update(accessory);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		accessoryDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		accessoryDao.delete(ids);
	}

	@Override
	public int deleteByFruitId(String fruitId) {
		// TODO Auto-generated method stub
		return accessoryDao.deleteByFruitId(fruitId);
	}

}

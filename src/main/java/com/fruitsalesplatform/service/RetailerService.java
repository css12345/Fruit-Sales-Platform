package com.fruitsalesplatform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Retailer;

public interface RetailerService {
	public Retailer get(Serializable id);
	public List<Retailer> find(@SuppressWarnings("rawtypes") Map map);
	public void insert(Retailer retailer);
	public void update(Retailer retailer);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public int count(@SuppressWarnings("rawtypes") Map map);
}

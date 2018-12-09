package com.fruitsalesplatform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Commodities;

public interface CommoditiesService {
	public Commodities get(Serializable id);
	public List<Commodities> find(@SuppressWarnings("rawtypes") Map map);
	public void insert(Commodities commodities);
	public void update(Commodities commodities);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public int count(@SuppressWarnings("rawtypes") Map map);
}

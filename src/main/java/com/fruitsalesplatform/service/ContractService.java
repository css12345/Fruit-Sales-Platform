package com.fruitsalesplatform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

public interface ContractService {
	public Contract get(Serializable contractId);
	public List<ContractVo> findContractList(@SuppressWarnings("rawtypes") Map map);
	public int count(@SuppressWarnings("rawtypes") Map map);
	public void insert(Contract contract, String[] commoditiesIdArrays, String[] priceArrays);
	public void insertMiddleTab(MiddleTab middleTab);
	public void update(Contract contract);
	public void deleteMiddleTab(Serializable contractId);
	public void deleteById(Serializable contractId);
	public String getMaxBarCode();
	public List<String> getRetailerAllContract(String string);
	public List<String> getCommodityAllContract(String string);
}

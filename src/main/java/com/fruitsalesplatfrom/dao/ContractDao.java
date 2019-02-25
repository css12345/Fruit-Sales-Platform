package com.fruitsalesplatfrom.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

public interface ContractDao extends BaseDao<Contract> {
	public int count(@SuppressWarnings("rawtypes") Map map);
	public List<ContractVo> findContractList(@SuppressWarnings("rawtypes") Map map);
	public void insertMiddleTab(MiddleTab middleTab);
	public int deleteMiddleTab(Serializable contractId);
	public String getMaxBarCode();
	public List<String> getRetailerAllContract(String retailerId);
	public List<String> getCommodityAllContract(String fruitId);
}

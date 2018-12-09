package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;
import com.fruitsalesplatform.service.ContractService;
import com.fruitsalesplatfrom.dao.ContractDao;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao contractDao;
	
	@Override
	public Contract get(Serializable contractId) {
		// TODO Auto-generated method stub
		return contractDao.get(contractId);
	}

	@Override
	public List<ContractVo> findContractList(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return contractDao.findContractList(map);
	}

	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return contractDao.count(map);
	}

	@Override
	public void insertMiddleTab(MiddleTab middleTab) {
		// TODO Auto-generated method stub
		contractDao.insertMiddleTab(middleTab);
	}

	@Override
	public void update(Contract contract) {
		// TODO Auto-generated method stub
		contractDao.update(contract);
	}

	@Override
	public void deleteMiddleTab(Serializable contractId) {
		// TODO Auto-generated method stub
		contractDao.deleteMiddleTab(contractId);
	}

	@Override
	public void deleteById(Serializable contractId) {
		// TODO Auto-generated method stub
		contractDao.deleteMiddleTab(contractId);
		
		contractDao.deleteById(contractId);
	}

	@Override
	public String getMaxBarCode() {
		// TODO Auto-generated method stub
		return contractDao.getMaxBarCode();
	}

	@Override
	public void insert(Contract contract, String[] commoditiesIdArrays, String[] priceArrays) {
		// TODO Auto-generated method stub
		contractDao.insert(contract);
		for(int i = 0;i < commoditiesIdArrays.length;i++) {
			MiddleTab middleTab = new MiddleTab();
			middleTab.setMiddleId(UUID.randomUUID().toString());
			middleTab.setContractId(contract.getContractId());
			middleTab.setFruitId(commoditiesIdArrays[i]);
			int number = Integer.parseInt("".equals(priceArrays[i]) ? "0" : priceArrays[i]);
			middleTab.setNumber(number);
			this.insertMiddleTab(middleTab);
		}
	}

}

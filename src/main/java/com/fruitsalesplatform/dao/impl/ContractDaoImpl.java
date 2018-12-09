package com.fruitsalesplatform.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;
import com.fruitsalesplatfrom.dao.ContractDao;

@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao {
	
	public ContractDaoImpl() {
		// TODO Auto-generated constructor stub
		this.setNameSpace("com.fruitsalesplatform.mapper.ContractMapper");
	}

	@Override
	public int count(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".count", map);
	}

	@Override
	public List<ContractVo> findContractList(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(this.getNameSpace() + ".findContractList", map);
	}

	@Override
	public void insertMiddleTab(MiddleTab middleTab) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(this.getNameSpace() + ".insertMiddleTab",middleTab);
	}

	@Override
	public int deleteMiddleTab(Serializable contractId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().delete(this.getNameSpace() + ".deleteMiddleTab", contractId);
	}

	@Override
	public String getMaxBarCode() {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getNameSpace() + ".getMaxBarCode");
	}

}

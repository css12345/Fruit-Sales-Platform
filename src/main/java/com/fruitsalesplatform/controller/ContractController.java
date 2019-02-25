package com.fruitsalesplatform.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.AccessoryService;
import com.fruitsalesplatform.service.CommoditiesService;
import com.fruitsalesplatform.service.ContractService;
import com.fruitsalesplatform.service.RetailerService;
import com.mysql.jdbc.StringUtils;

@Controller
public class ContractController extends BaseController {
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private RetailerService retailerService;
	
	@Autowired
	private CommoditiesService commoditiesService;
	
	@Autowired
	private AccessoryService accessoryService;
	
	@RequestMapping(value = "contract/getCommodityAllContract",method = RequestMethod.POST)
	public @ResponseBody List<String> getCommodityAllContract(@RequestBody String fruitId) {
		return contractService.getCommodityAllContract(JSONObject.parseObject(fruitId).getString("fruitId"));
	}
	
	@RequestMapping(value = "contract/getRetailerAllContract",method = RequestMethod.POST)
	public @ResponseBody List<String> getRetailerAllContract(@RequestBody String retailerId) {
		return contractService.getRetailerAllContract(JSONObject.parseObject(retailerId).getString("retailerId"));
	}
	
	@RequestMapping(value = "contract/edit.action",method = RequestMethod.POST)
	public String editContract(Model model,Contract contract,String retailerId,String[] commoditiesIdArrays,String[] priceArrays) {
		String contractId = contract.getContractId();
		contractService.deleteById(contractId);
		
		contract.setRetailer(retailerService.get(retailerId));
		contractService.insert(contract,commoditiesIdArrays,priceArrays);
		model.addAttribute("resultMessage", "修改成功！");
		Contract newContract = contractService.get(contractId);
		model.addAttribute("contract",newContract);
		return "/contract/editContract.jsp";
	}
	
	@RequestMapping("/contract/toEditPage.action")
	public String toEditPage(Model model,String contractId) {
		model.addAttribute("contract", contractService.get(contractId));
		return "/contract/editContract.jsp";
	}
	
	@RequestMapping(value = "/contract/delete.action",method = RequestMethod.POST)
	public String delete(Model model,ContractVo contractVo) {
		contractService.deleteById(contractVo.getContractId());
		ContractVo queryContract = new ContractVo();
		queryContract.setType(-1);
		queryContract.setStartPage(contractVo.getStartPage());
		queryContract.setCurrentPage(contractVo.getCurrentPage());
		queryContract.setPageSize(contractVo.getPageSize());
		return list(model, queryContract, null, null);
	}
	
	@RequestMapping("/contract/getContractDetail.action")
	public String getContractDetail(Model model,String contractId) {
		Contract contract = contractService.get(contractId);
		model.addAttribute("contract", contract);
		return "/contract/contractDetail.jsp";
	}
	
	@RequestMapping(value = "/contract/add.action",method = RequestMethod.POST)
	public String add(Model model,Contract contract,String retailerId,String[] commoditiesIdArrays,String[] priceArrays) {
		contract.setRetailer(retailerService.get(retailerId));
		String barCode = getCode();
		contract.setBarCode(barCode);
		contract.setContractId(UUID.randomUUID().toString());
		contract.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		contractService.insert(contract,commoditiesIdArrays,priceArrays);
		model.addAttribute("resultMessage", "添加成功！合同编号为" + barCode);
		return "/contract/addContract.jsp";
	}
	
	private String getCode() {
		// TODO Auto-generated method stub
		String codeHead = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String barCode = "";
		String maxBarCode = contractService.getMaxBarCode();
		if(!StringUtils.isNullOrEmpty(maxBarCode)) {
			if(maxBarCode.substring(0,8).equals(codeHead)) 
				maxBarCode = maxBarCode.substring(8);
			else
				maxBarCode = "0";
		}
		else
			maxBarCode = "0";
		int maxNumber = Integer.parseInt(maxBarCode);
		int newNumber = maxNumber + 1;
		barCode = String.format("%04d", newNumber);
		barCode = codeHead + barCode;
		return barCode;
	}

	@RequestMapping(value = "/contract/getCommoditiesAndAccessory.action",method = RequestMethod.POST) 
	public @ResponseBody List<Map<String,Object>> getCommoditiesAndAccessory(String[] arrays) {
		List<Map<String, Object>> cList  = new ArrayList<>();
		for(String fruitId : arrays) {
			Map<String, Object> cMap = new HashMap<>();
			cMap.put("commodities", commoditiesService.get(fruitId));
			Map<String, String> param = new HashMap<>();
			param.put("fruitId", fruitId);
			cMap.put("accessory", accessoryService.find(param));
			cList.add(cMap);
		}
		return cList;
	}
	
	@RequestMapping(value = "/contract/getAllCommodities.action",method = RequestMethod.POST) 
	public @ResponseBody List<Commodities> getAllCommodities(@RequestBody String json) {
		Map<String,Object> param = new HashMap<>();
		if(!StringUtils.isNullOrEmpty(json)){
			String name = JSONObject.parseObject(json).getString("name");
			if(!StringUtils.isNullOrEmpty(name))
				param.put("name", "%" + name + "%");
		}
		
		List<Commodities> commoditiesList = commoditiesService.find(param);
		return commoditiesList;
	}
	
	@RequestMapping(value = "/contract/getAllRetailer.action",method = RequestMethod.POST) 
	public @ResponseBody List<Retailer> getAllRetailer(@RequestBody String json) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", 1);
		if(!StringUtils.isNullOrEmpty(json)) {
			String name = JSONObject.parseObject(json).getString("name");
			if(!StringUtils.isNullOrEmpty(name))
				param.put("name", "%" + name + "%");
		}
			
		List<Retailer> retailerList = retailerService.find(param);
		return retailerList;
	}
	
	@RequestMapping("/contract/toAddPage.action")
	public String toAddPage() {
		return "/contract/addContract.jsp";
	}
	
	@RequestMapping("/contract/list.action")
	public String list(Model model,ContractVo contractVo,String startTime,String endTime) {
		Map<String,Object> map = this.contractVoToMap(contractVo);
		if (startTime != null && !startTime.equals(""))
			map.put("startTime", startTime);
		if (endTime != null && !endTime.equals(""))
			map.put("endTime", endTime);
		map.put("startPage",contractVo.getStartPage());
		map.put("pageSize", contractVo.getPageSize());
		
		List<ContractVo> contractVoList = contractService.findContractList(map);
		
		model.addAttribute("contractVo", contractVo);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		
		model.addAttribute("list", contractVoList.size() < 1 ? null : contractVoList);
		model.addAttribute("currentPage", contractVo.getCurrentPage());
		model.addAttribute("startPage", contractVo.getStartPage());
		int countNumber = contractService.count(map);
		model.addAttribute("countNumber", countNumber);
		int pageSize = contractVo.getPageSize();
		model.addAttribute("pageSize", pageSize);
		int sumPageNumber;
		if (countNumber % pageSize == 0)
			sumPageNumber = countNumber / pageSize;
		else
			sumPageNumber = countNumber / pageSize + 1;
		model.addAttribute("sumPageNumber", sumPageNumber);
		return "/contract/contractHome.jsp";
	}
	
	private Map<String, Object> contractVoToMap(ContractVo contractVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("barCode", checkStringIsEmpty(contractVo.getBarCode()));
		map.put("type", contractVo.getType() == -1 ? null : contractVo.getType());
		map.put("retailerName", checkStringIsEmpty(contractVo.getRetailerName()));
		return map;
	}

	private String checkStringIsEmpty(String param) {
		// TODO Auto-generated method stub
		return param == null ? null : (param.equals("") ? null : "%" + param + "%");
	}
}

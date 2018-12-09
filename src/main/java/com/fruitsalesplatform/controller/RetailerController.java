package com.fruitsalesplatform.controller;

import java.text.SimpleDateFormat;
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
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;

@Controller
public class RetailerController extends BaseController {
	@Autowired
	RetailerService retailerService;
	
	@RequestMapping(value = "/retailer/add.action",method = RequestMethod.POST) 
	public String add(Model model,Retailer retailer) {
		retailer.setRetailerId(UUID.randomUUID().toString());
		retailer.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		retailerService.insert(retailer);
		Retailer queryRetailer = new Retailer();
		queryRetailer.setStartPage(retailer.getStartPage());
		queryRetailer.setPageSize(retailer.getPageSize());
		queryRetailer.setCurrentPage(retailer.getCurrentPage());
		queryRetailer.setStatus(-1);
		return list(model, queryRetailer, null, null);
	}
	
	@RequestMapping(value = "/retailer/delete.action",method = RequestMethod.POST)
	public String delete(Model model,Retailer retailer) {
		retailerService.deleteById(retailer.getRetailerId());
		Retailer queryRetailer = new Retailer();
		queryRetailer.setStartPage(retailer.getStartPage());
		queryRetailer.setPageSize(retailer.getPageSize());
		queryRetailer.setCurrentPage(retailer.getCurrentPage());
		queryRetailer.setStatus(-1);
		return list(model, queryRetailer, null, null);
	}
	
	@RequestMapping(value = "/retailer/editRetailer.action",method = RequestMethod.POST)
	public @ResponseBody Retailer editRetailer(@RequestBody String json) {
		//System.out.println(json);
		String id = JSONObject.parseObject(json).getString("id");
		return retailerService.get(id);
	}
	
	@RequestMapping(value = "/retailer/edit.action",method = RequestMethod.POST)
	public String edit(Model model,Retailer retailer){
		retailerService.update(retailer);
		
		Retailer queryRetailer = new Retailer();
		queryRetailer.setStartPage(retailer.getStartPage());
		queryRetailer.setPageSize(retailer.getPageSize());
		queryRetailer.setCurrentPage(retailer.getCurrentPage());
		queryRetailer.setStatus(-1);
		return list(model, queryRetailer, null, null);
	}

	@RequestMapping("/retailer/list.action")
	public String list(Model model, Retailer retailer, String startTime, String endTime) {
		Map<String, Object> map = this.retailerToMap(retailer);
		if (startTime != null && !startTime.equals(""))
			map.put("startTime", startTime);
		if (endTime != null && !endTime.equals(""))
			map.put("endTime", endTime);
		map.put("startPage",retailer.getStartPage());
		map.put("pageSize", retailer.getPageSize());
		
		List<Retailer> retailerList = retailerService.find(map);
		
		model.addAttribute("retailer", retailer);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		
		model.addAttribute("list", retailerList.size() < 1 ? null : retailerList);
		model.addAttribute("currentPage", retailer.getCurrentPage());
		model.addAttribute("startPage", retailer.getStartPage());
		int countNumber = retailerService.count(map);
		model.addAttribute("countNumber", countNumber);
		int pageSize = retailer.getPageSize();
		model.addAttribute("pageSize", pageSize);
		int sumPageNumber;
		if (countNumber % pageSize == 0)
			sumPageNumber = countNumber / pageSize;
		else
			sumPageNumber = countNumber / pageSize + 1;
		model.addAttribute("sumPageNumber", sumPageNumber);
		return "/retailer/retailerHome.jsp";
	}

	private Map<String, Object> retailerToMap(Retailer retailer) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", checkStringIsEmpty(retailer.getName()));
		map.put("address", checkStringIsEmpty(retailer.getAddress()));
		map.put("status", retailer.getStatus() == -1 ? null : retailer.getStatus());
		map.put("telephone", checkStringIsEmpty(retailer.getTelephone()));
		map.put("createTime", (retailer.getCreateTime() == null || retailer.getCreateTime().equals("")) ? null
				: retailer.getCreateTime());
		return map;
	}

	private String checkStringIsEmpty(String param) {
		// TODO Auto-generated method stub
		return param == null ? null : (param.equals("") ? null : "%" + param + "%");
	}
}

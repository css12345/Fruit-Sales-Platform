package com.fruitsalesplatform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.AccessoryService;
import com.fruitsalesplatform.service.CommoditiesService;

@Controller
public class CommoditiesController extends BaseController {
	
	@Autowired
	private CommoditiesService commoditiesService;
	
	@Autowired
	private AccessoryService accessoryService;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value = "/commodities/add.action",method = RequestMethod.POST) 
	public String add(Model model,Commodities commodities) {
		commodities.setFruitId(UUID.randomUUID().toString());
		commodities.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		commoditiesService.insert(commodities);
		Commodities queryCommodities = new Commodities();
		queryCommodities.setStartPage(commodities.getStartPage());
		queryCommodities.setPageSize(commodities.getPageSize());
		queryCommodities.setCurrentPage(commodities.getCurrentPage());
		return list(model, queryCommodities, 0.0, 0.0, null, null);
	}
	
	@RequestMapping(value = "/commodities/delete.action",method = RequestMethod.POST)
	public String delete(Model model,Commodities commodities) {
		commoditiesService.deleteById(commodities.getFruitId());
		
		int result = accessoryService.deleteByFruitId(commodities.getFruitId());
		log.info("delete fruitId=" + commodities.getFruitId() + "'s accessorys number:" + result);
		
		Commodities queryCommodities = new Commodities();
		queryCommodities.setStartPage(commodities.getStartPage());
		queryCommodities.setPageSize(commodities.getPageSize());
		queryCommodities.setCurrentPage(commodities.getCurrentPage());
		return list(model, queryCommodities, 0.0, 0.0, null, null);
	}
	
	@RequestMapping(value = "/commodities/editCommodities.action",method = RequestMethod.POST)
	public @ResponseBody Commodities editcommodities(@RequestBody String json) {
		//System.out.println(json);
		String id = JSONObject.parseObject(json).getString("id");
		return commoditiesService.get(id);
	}
	
	@RequestMapping(value = "/commodities/edit.action",method = RequestMethod.POST)
	public String edit(Model model,Commodities commodities){
		commoditiesService.update(commodities);
		
		Commodities queryCommodities = new Commodities();
		queryCommodities.setStartPage(commodities.getStartPage());
		queryCommodities.setPageSize(commodities.getPageSize());
		queryCommodities.setCurrentPage(commodities.getCurrentPage());
		return list(model, queryCommodities, 0.0, 0.0, null, null);
	}
	
	@RequestMapping("/commodities/list.action")
	public String list(Model model, Commodities commodities, 
			@RequestParam(defaultValue = "0.0") double startPrice,
			@RequestParam(defaultValue = "0.0") double endPrice,
			String startTime, String endTime) {
		Map<String, Object> map = this.commoditiesToMap(commodities);
		if (startTime != null && !startTime.equals(""))
			map.put("startTime", startTime);
		if (endTime != null && !endTime.equals(""))
			map.put("endTime", endTime);
		if(startPrice > 0.0)
			map.put("startPrice", startPrice);
		if(endPrice > 0.0)
			map.put("endPrice", endPrice);
		map.put("startPage",commodities.getStartPage());
		map.put("pageSize", commodities.getPageSize());
		
		List<Commodities> commoditiesList = commoditiesService.find(map);
		model.addAttribute("commodities", commodities);
		model.addAttribute("startPrice", startPrice);
		model.addAttribute("endPrice", endPrice);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("list", commoditiesList.size() < 1 ? null : commoditiesList);
		model.addAttribute("currentPage", commodities.getCurrentPage());
		model.addAttribute("startPage", commodities.getStartPage());
		int countNumber = commoditiesService.count(map);
		model.addAttribute("countNumber", countNumber);
		int pageSize = commodities.getPageSize();
		model.addAttribute("pageSize", pageSize);
		int sumPageNumber;
		if (countNumber % pageSize == 0)
			sumPageNumber = countNumber / pageSize;
		else
			sumPageNumber = countNumber / pageSize + 1;
		model.addAttribute("sumPageNumber", sumPageNumber);
		return "/commodities/commoditiesHome.jsp";
	}

	private Map<String, Object> commoditiesToMap(Commodities commodities) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", checkStringIsEmpty(commodities.getName()));
		map.put("locality", checkStringIsEmpty(commodities.getLocality()));
		map.put("createTime", (commodities.getCreateTime() == null || commodities.getCreateTime().equals("")) ? null
				: commodities.getCreateTime());
		return map;
	}

	private String checkStringIsEmpty(String param) {
		// TODO Auto-generated method stub
		return param == null ? null : (param.equals("") ? null : "%" + param + "%");
	}
}

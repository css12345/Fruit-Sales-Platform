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
import org.springframework.web.bind.annotation.RequestMapping;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;

@Controller
public class AccessoryController extends BaseController {
	@Autowired
	private AccessoryService accessoryService;
	
	@RequestMapping("/accessory/list.action")
	public String list(Model model,Accessory accessory) {
		Map<String, Object> map = new HashMap<>();
		map.put("fruitId", accessory.getFruitId());
		List<Accessory> accessorieList = accessoryService.find(map);
		model.addAttribute("fruitId", accessory.getFruitId());
		model.addAttribute("list", accessorieList.size() < 1 ? null : accessorieList);
		model.addAttribute("sumPrice", sumPrice(accessorieList));
		return "/accessory/accessoryHome.jsp";
	}

	private double sumPrice(List<Accessory> accessorieList) {
		// TODO Auto-generated method stub
		double sum = 0;
		for(Accessory accessory : accessorieList)
			sum += accessory.getPrice();
		return sum;
	}
	
	@RequestMapping("/accessory/add.action")
	public String add(Model model,Accessory accessory) {
		accessory.setAccessoryId(UUID.randomUUID().toString());
		accessory.setFruitId(accessory.getFruitId());
		accessory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		accessoryService.insert(accessory);
		return list(model, accessory);
	}
	
	@RequestMapping("/accessory/delete.action")
	public String delte(Model model,Accessory accessory) {
		accessoryService.deleteById(accessory.getAccessoryId());
		return list(model, accessory);
	}
	
	@RequestMapping("/accessory/deleteList.action")
	public String deleteList(Model model,String[] arrays,Accessory accessory) {
		accessoryService.delete(arrays);
		return list(model, accessory);
	}
}

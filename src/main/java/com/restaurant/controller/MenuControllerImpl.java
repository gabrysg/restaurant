package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.MenuInfo;
import com.restaurant.service.MenuService;

@Controller
public class MenuControllerImpl implements MenuController {

	@Autowired
	private MenuService menuService;

	@Override
	@RequestMapping({ "/menu" })
	public String getActualMenu(Model model) {
		MenuInfo menuInfo = menuService.getActualMenuInfo();
		model.addAttribute("menuInfo", menuInfo);
		return "menu";
	}

}

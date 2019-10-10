package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public interface MenuController {

	@RequestMapping({ "/menu" })
	public String getActualMenu(Model model);

}

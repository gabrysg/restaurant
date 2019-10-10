package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestaurantControllerImpl implements RestaurantController {

	@Override
	@RequestMapping({ "/about" })
	public String getAboutUs() {
		return "about";
	}
}

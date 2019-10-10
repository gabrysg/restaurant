package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public interface RestaurantController {

	@RequestMapping({ "/about" })
	public String getAboutUs();

}

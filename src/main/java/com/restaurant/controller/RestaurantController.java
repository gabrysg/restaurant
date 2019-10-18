package com.restaurant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurant.model.MailConfigurationInfo;

@Controller
public interface RestaurantController {

	@GetMapping({ "/about" })
	public String getAboutUs();

	@GetMapping({ "/configuration" })
	public String getRestaurantConfiguration(Model model);

	@PostMapping({ "/useDefaultConfiguration" })
	public String useDefaultRestaurantConfiguration(Model model);

	@PostMapping({ "/saveConfiguration" })
	public String saveRestaurantConfiguration(HttpServletRequest request, Model model,
			@ModelAttribute("mailConfigurationInfo") @Valid MailConfigurationInfo mailConfigurationInfo,
			BindingResult result);
}

package com.restaurant.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurant.model.MailConfigurationInfo;
import com.restaurant.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RestaurantControllerImpl implements RestaurantController {

	@Autowired
	private MailService mailService;

	@Override
	@GetMapping({ "/about" })
	public String getAboutUs() {
		return "about";
	}

	@Override
	@GetMapping({ "/configuration" })
	public String getRestaurantConfiguration(Model model) {
		model.addAttribute("mailConfigurationInfo", new MailConfigurationInfo());
		return "configuration";
	}

	@Override
	@PostMapping({ "/useDefaultConfiguration" })
	public String useDefaultRestaurantConfiguration(Model model) {
		MailService.mailConfigurationInfo = null;
		MailConfigurationInfo mailConfigurationInfo = mailService.getDefaultConfigurationInfo();
		model.addAttribute("mailConfigurationInfo", mailConfigurationInfo);
		return "defaultConfigurationConfirmation";
	}

	@Override
	@PostMapping({ "/saveConfiguration" })
	public String saveRestaurantConfiguration(HttpServletRequest request, Model model,
			@ModelAttribute("mailConfigurationInfo") @Valid MailConfigurationInfo mailConfigurationInfo,
			BindingResult result) {

		MailService.mailConfigurationInfo = mailConfigurationInfo;
		// send confirmation email
		try {
			mailService.sendMailToChangedOwner(mailConfigurationInfo);
		} catch (MessagingException | MailSendException e) {
			log.error("Can not sand confirmation email - {}", e.getMessage());
			MailService.mailConfigurationInfo = null;

			ObjectError error = new ObjectError("error",
					"Can not sand confirmation email - " + e.getLocalizedMessage());
			result.addError(error);
			mailConfigurationInfo.setError("Can not sand confirmation email - " + e.getLocalizedMessage());

			model.addAllAttributes(result.getModel());
			model.addAttribute("mailConfigurationInfo", mailConfigurationInfo);
			return "configuration";
		}

		MailService.mailConfigurationInfo = mailConfigurationInfo;
		model.addAttribute("mailConfigurationInfo", mailConfigurationInfo);
		return "savedConfigurationConfirmation";
	}
}

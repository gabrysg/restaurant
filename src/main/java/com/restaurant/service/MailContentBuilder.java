package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.restaurant.model.MailConfigurationInfo;
import com.restaurant.model.OrderInfo;

@Service
public class MailContentBuilder {

	private TemplateEngine templateEngine;

	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
		templateEngine.addDialect(new Java8TimeDialect());
	}

	public String build(OrderInfo orderInfo) {
		Context context = new Context();
		context.setVariable("orderInfo", orderInfo);
		return templateEngine.process("emailTemplate", context);
	}

	public String buildForOwner(OrderInfo orderInfo) {
		Context context = new Context();
		context.setVariable("orderInfo", orderInfo);
		return templateEngine.process("emailTemplateRestaurant", context);
	}

	public String buildConfirmationConfigurationChanged(MailConfigurationInfo mailConfigurationInfo) {
		Context context = new Context();
		context.setVariable("mailConfigurationInfo", mailConfigurationInfo);
		return templateEngine.process("emailTemplateConfigurationConfirmation", context);
	}

}

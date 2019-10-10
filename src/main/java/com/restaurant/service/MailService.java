package com.restaurant.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.restaurant.model.OrderInfo;

@Service
public class MailService {

	@Value("${spring.mail.username}")
	private String restaurantMailUsername;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	@Autowired
	MessageSource messageSource;

	public void sendMail(OrderInfo orderInfo) throws MessagingException {
		sendMailWithOrder(orderInfo);
		sendMailToOwner(orderInfo);
	}

	private void sendMailWithOrder(OrderInfo orderInfo) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(orderInfo.getName());
		helper.setFrom(getRestaurantMailUsername());
		helper.setSubject(messageSource.getMessage("emailSubject", null, LocaleContextHolder.getLocale()));
		helper.setText(mailContentBuilder.build(orderInfo), true);
		javaMailSender.send(message);
	}

	private void sendMailToOwner(OrderInfo orderInfo) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(getRestaurantMailUsername());
		helper.setFrom(getRestaurantMailUsername());
		helper.setSubject(messageSource.getMessage("newOrderTitle", null, LocaleContextHolder.getLocale()));
		helper.setText(mailContentBuilder.buildForOwner(orderInfo), true);
		javaMailSender.send(message);
	}

	public String getRestaurantMailUsername() {
		return restaurantMailUsername;
	}

	public void setRestaurantMailUsername(String restaurantMailUsername) {
		this.restaurantMailUsername = restaurantMailUsername;
	}

}

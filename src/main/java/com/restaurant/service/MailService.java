package com.restaurant.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.restaurant.model.MailConfigurationInfo;
import com.restaurant.model.OrderInfo;

@Service
public class MailService {

	public static MailConfigurationInfo mailConfigurationInfo;

	@Value("${spring.mail.host}")
	private String restaurantMailHost;

	@Value("${spring.mail.port}")
	private int restaurantMailPort;

	@Value("${spring.mail.username}")
	private String restaurantMailUsername;

	@Value("${spring.mail.password}")
	private String restaurantMailPassword;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean restaurantMailPropertyTlsEnabled;

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	@Autowired
	MessageSource messageSource;

	public void sendMail(OrderInfo orderInfo) throws MessagingException {
		setMailConfiguration();
		sendMailWithOrder(orderInfo);
		sendMailToOwner(orderInfo);
	}

	private void setMailConfiguration() {
		if (mailConfigurationInfo != null) {
			javaMailSender.setHost(mailConfigurationInfo.getMailHost());
			javaMailSender.setPort(mailConfigurationInfo.getMailPort());
			javaMailSender.setUsername(mailConfigurationInfo.getMailUsername());
			javaMailSender.setPassword(mailConfigurationInfo.getMailPassword());
			javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable",
					mailConfigurationInfo.isMailSmtpStartTlsEnable());
			javaMailSender.getJavaMailProperties().put("mail.smtp.ssl.trust", mailConfigurationInfo.getMailHost());
		} else {
			javaMailSender.setHost(getRestaurantMailHost());
			javaMailSender.setPort(getRestaurantMailPort());
			javaMailSender.setUsername(getRestaurantMailUsername());
			javaMailSender.setPassword(getRestaurantMailPassword());
			javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable",
					isRestaurantMailPropertyTlsEnabled());
			javaMailSender.getJavaMailProperties().put("mail.smtp.ssl.trust", getRestaurantMailHost());
		}
	}

	private void sendMailWithOrder(OrderInfo orderInfo) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(orderInfo.getName());
		helper.setFrom(getActualRestaurantMailUsername());
		helper.setSubject(messageSource.getMessage("emailSubject", null, LocaleContextHolder.getLocale()));
		helper.setText(mailContentBuilder.build(orderInfo), true);
		javaMailSender.send(message);
	}

	private void sendMailToOwner(OrderInfo orderInfo) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(getActualRestaurantMailUsername());
		helper.setFrom(getActualRestaurantMailUsername());
		helper.setSubject(messageSource.getMessage("newOrderTitle", null, LocaleContextHolder.getLocale()));
		helper.setText(mailContentBuilder.buildForOwner(orderInfo), true);
		javaMailSender.send(message);
	}

	public void sendMailToChangedOwner(MailConfigurationInfo mailConfigurationInfo) throws MessagingException {
		setMailConfiguration();
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(mailConfigurationInfo.getMailUsername());
		helper.setFrom(mailConfigurationInfo.getMailUsername());
		helper.setSubject(
				messageSource.getMessage("configurationConfirmationSubject", null, LocaleContextHolder.getLocale()));
		helper.setText(mailContentBuilder.buildConfirmationConfigurationChanged(mailConfigurationInfo), true);
		javaMailSender.send(message);
	}

	public MailConfigurationInfo getDefaultConfigurationInfo() {
		MailConfigurationInfo mailConfigurationInfo = new MailConfigurationInfo();
		mailConfigurationInfo.setMailHost(getRestaurantMailHost());
		mailConfigurationInfo.setMailPort(getRestaurantMailPort());
		mailConfigurationInfo.setMailUsername(getRestaurantMailUsername());
		mailConfigurationInfo.setMailPassword(getRestaurantMailPassword());
		mailConfigurationInfo.setMailSmtpStartTlsEnable(isRestaurantMailPropertyTlsEnabled());
		return mailConfigurationInfo;
	}

	public String getActualRestaurantMailUsername() {
		return (mailConfigurationInfo != null) ? mailConfigurationInfo.getMailUsername() : getRestaurantMailUsername();
	}

	public String getRestaurantMailUsername() {
		return restaurantMailUsername;
	}

	public String getRestaurantMailHost() {
		return restaurantMailHost;
	}

	public int getRestaurantMailPort() {
		return restaurantMailPort;
	}

	public String getRestaurantMailPassword() {
		return restaurantMailPassword;
	}

	public boolean isRestaurantMailPropertyTlsEnabled() {
		return restaurantMailPropertyTlsEnabled;
	}

}

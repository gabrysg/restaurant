package com.restaurant.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MailConfigurationInfo {

	private String mailHost;
	private int mailPort;
	private String mailUsername;
	private String mailPassword;
	private boolean mailSmtpStartTlsEnable;
	private LocalDateTime createdDate;
	private String error;
}

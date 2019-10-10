package com.restaurant.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.restaurant.exception.RestaurantException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestaurantExceptionController {

	@ExceptionHandler(MessagingException.class)
	public void handleMessagingException(MessagingException ex) {
		log.error("MessagingException occured.", ex);
	}

	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleMailSendException(MailSendException ex) {
		log.error("MailSendException occured. {}, {}", ex.getMessage(), ex.getFailedMessages().toString());
		log.debug("RestaurantException with reason={}", ex.getMessage());

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("status", HttpStatus.BAD_REQUEST);
		mav.addObject("message", ex.getFailedMessages().toString());
		mav.setViewName("error");
		return mav;
	}

	@ExceptionHandler(RestaurantException.class)
	public ModelAndView handleRestaurantException(HttpServletRequest request, RestaurantException ex) {
		log.info("RestaurantException occured:: URL={}", request.getRequestURL());
		log.debug("RestaurantException with reason={}", ex.getMessage(), ex);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("status", ex.getStatus());
		mav.setViewName("error");
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception ex) {
		log.info("Exception occured:: URL={}", request.getRequestURL());
		return "error";
	}
}

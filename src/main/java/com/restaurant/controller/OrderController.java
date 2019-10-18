package com.restaurant.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.MenuInfo;
import com.restaurant.model.OrderInfo;

@Controller
public interface OrderController {

	@RequestMapping({ "/order" })
	public String getActualMenu(Model model);

	@PostMapping({ "/improveOrder" })
	public String improveOrder(HttpServletRequest request, Model model,
			@ModelAttribute("orderInfo") @Valid OrderInfo orderInfo, BindingResult result);

	@PostMapping({ "/orderPreview" })
	public String showOrderPreview(HttpServletRequest request, Model model,
			@ModelAttribute("menuInfo") @Valid MenuInfo menuInfo, BindingResult result);

	@PostMapping({ "/orderSend" })
	public String sendOrder(HttpServletRequest request, Model model,
			@ModelAttribute("orderInfo") @Valid OrderInfo orderInfo, BindingResult result) throws MessagingException;

	@RequestMapping({ "/orders" })
	public String getAllOrders(Model model);

	@RequestMapping({ "/orderView" })
	public String getOrderById(@PathParam("id") long id, Model model, HttpServletResponse response) throws IOException;
}

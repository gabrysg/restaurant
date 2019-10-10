package com.restaurant.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.MenuInfo;
import com.restaurant.model.OrderInfo;
import com.restaurant.service.MailService;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;

@Controller
public class OrderControllerImpl implements OrderController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MailService mailService;

	@Override
	@RequestMapping({ "/order" })
	public String getActualMenu(Model model) {
		MenuInfo menuInfo = menuService.getActualMenuInfo();
		model.addAttribute("menuInfo", menuInfo);
		return "order";
	}

	@Override
	@PostMapping({ "/improveOrder" })
	public String improveOrder(HttpServletRequest request, Model model,
			@ModelAttribute("orderInfo") @Valid OrderInfo orderInfo, BindingResult result) {
		MenuInfo menuInfo = menuService.getActualMenuWithSelections(orderInfo);
		menuInfo.setOrderName(orderInfo.getName());
		model.addAttribute("menuInfo", menuInfo);
		return "order";
	}

	@Override
	@PostMapping({ "/orderPreview" })
	public String showOrderPreview(HttpServletRequest request, Model model,
			@ModelAttribute("menuInfo") @Valid MenuInfo menuInfo, BindingResult result) {
		OrderInfo orderInfo = menuService.getSelectedOrder(menuInfo);
		orderInfo.setName(menuInfo.getOrderName());
		model.addAttribute("orderInfo", orderInfo);
		return "orderPreview";
	}

	@Override
	@PostMapping({ "/sendOrder" })
	public String sendOrder(HttpServletRequest request, Model model,
			@ModelAttribute("orderInfo") @Valid OrderInfo orderInfo, BindingResult result) throws MessagingException {

		if (result.hasErrors()) {
			model.addAttribute("orderInfo", orderInfo);
			return "orderPreview";
		}
		orderInfo.setDate(LocalDateTime.now());
		orderService.saveOrder(orderInfo);
		mailService.sendMail(orderInfo);

		return "sendOrder";
	}

	@Override
	@RequestMapping({ "/orders" })
	public String getAllOrders(Model model) {
		List<OrderInfo> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "orders";
	}

	@Override
	@RequestMapping({ "/orderView" })
	public String getOrderById(@PathParam("id") long id, Model model, HttpServletResponse response) throws IOException {
		OrderInfo order = orderService.getOrder(id);
		model.addAttribute("orderInfo", order);
		return "orderView";
	}
}

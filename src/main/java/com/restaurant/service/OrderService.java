package com.restaurant.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.entity.Order;
import com.restaurant.model.OrderInfo;
import com.restaurant.repository.OrderRepository;
import com.restaurant.util.OrderConverter;

@Service
public class OrderService {

	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void saveOrder(OrderInfo orderInfo) {
		Order order = orderConverter.convertToOrder(orderInfo);
		order.setDate(LocalDateTime.now());
		orderRepository.save(order);
	}

	@Transactional
	public List<OrderInfo> getAllOrders() {
		return orderConverter.convertToOrderInfos((List<Order>) orderRepository.findAll());
	}

	@Transactional
	public OrderInfo getOrder(long id) {
		Optional<Order> opOrder = orderRepository.findById(id);
		return opOrder.isPresent() ? orderConverter.convertToOrderInfo(opOrder.get()) : new OrderInfo();
	}
}

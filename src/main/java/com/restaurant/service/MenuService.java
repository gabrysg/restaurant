package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.restaurant.entity.Menu;
import com.restaurant.exception.RestaurantException;
import com.restaurant.model.MenuInfo;
import com.restaurant.model.OrderInfo;
import com.restaurant.repository.MenuRepository;
import com.restaurant.util.MenuConverter;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private MenuConverter menuConverter;

	public MenuInfo getActualMenuInfo() {
		return menuConverter.convertToMenuInfo(getActualMenu());
	}

	public MenuInfo getActualMenuWithSelections(OrderInfo orderInfo) {
		return menuConverter.convertToMenuInfoWithSelections(getActualMenu(), orderInfo);
	}

	public OrderInfo getSelectedOrder(MenuInfo menuInfo) {
		return menuConverter.convertToOrderInfo(menuInfo);

	}

	private Menu getActualMenu() {
		Menu actualMenu = menuRepository.findOneByActualTrue();

		if (actualMenu == null) {
			throw new RestaurantException("No actual menu found", HttpStatus.NOT_FOUND);
		}
		return actualMenu;
	}
}

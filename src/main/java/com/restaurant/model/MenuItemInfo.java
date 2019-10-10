package com.restaurant.model;

import lombok.Data;

@Data
public class MenuItemInfo {

	private String name;
	private double price;
	private int amount;
	private boolean selected = false;

	public MenuItemInfo() {
		this.name = "";
		this.price = 0;
		this.amount = 0;
	}

	public MenuItemInfo(String name, double price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
}

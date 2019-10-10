package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Pizza extends MenuItem {

	@ManyToMany(mappedBy = "pizzas")
	private List<Menu> menu;

	public Pizza() {
		super();
	}

	public Pizza(String name, double price, int amount) {
		super(name, price, amount);
	}

	public Pizza(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}

}

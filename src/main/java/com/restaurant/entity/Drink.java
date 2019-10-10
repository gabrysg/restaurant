package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Drink extends MenuItem {

	@ManyToMany(mappedBy = "drinks")
	private List<Menu> menu;

	public Drink() {
		super();
	}

	public Drink(String name, double price, int amount) {
		super(name, price, amount);
	}

	public Drink(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}
}

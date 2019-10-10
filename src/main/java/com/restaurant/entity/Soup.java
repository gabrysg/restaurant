package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Soup extends MenuItem {

	@ManyToMany(mappedBy = "soups")
	private List<Menu> menu;

	public Soup() {
		super();
	}

	public Soup(String name, double price, int amount) {
		super(name, price, amount);
	}

	public Soup(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}
}

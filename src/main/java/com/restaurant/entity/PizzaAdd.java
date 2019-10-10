package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class PizzaAdd extends MenuItem {

	@ManyToMany(mappedBy = "pizzaAdds")
	private List<Menu> menu;

	public PizzaAdd() {
		super();
	}

	public PizzaAdd(String name, double price, int amount) {
		super(name, price, amount);
	}

	public PizzaAdd(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}
}

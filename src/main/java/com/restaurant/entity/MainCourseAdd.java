package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class MainCourseAdd extends MenuItem {

	@ManyToMany(mappedBy = "mainCourseAdds")
	private List<Menu> menu;

	public MainCourseAdd() {
		super();
	}

	public MainCourseAdd(String name, double price, int amount) {
		super(name, price, amount);
	}

	public MainCourseAdd(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}
}

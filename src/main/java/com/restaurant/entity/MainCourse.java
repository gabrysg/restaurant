package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class MainCourse extends MenuItem {

	@ManyToMany(mappedBy = "mainCourses")
	private List<Menu> menu;

	public MainCourse() {
		super();
	}

	public MainCourse(String name, double price, int amount) {
		super(name, price, amount);
	}

	public MainCourse(String name, double price, int amount, Order order) {
		super(name, price, amount, order);
	}
}

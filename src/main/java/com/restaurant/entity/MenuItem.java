package com.restaurant.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private double price;
	private int amount;

	@ManyToOne
	@JoinColumn(name = "order_table_id")
	private Order ordered;

	public MenuItem() {
		this.name = "";
		this.price = 0;
		this.amount = 0;
	}

	public MenuItem(String name, double price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public MenuItem(String name, double price, int amount, Order order) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.ordered = order;
	}
}

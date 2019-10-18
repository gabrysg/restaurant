package com.restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;

	@OneToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private Menu menu;

	@OneToOne
	@JoinColumn(name = "ordered_id", referencedColumnName = "id")
	private Order ordered;

	public Note() {
		this.description = "";
	}

	public Note(String description) {
		this.description = description;
	}

	public Note(String description, Order order) {
		this.description = description;
		this.ordered = order;
	}
}

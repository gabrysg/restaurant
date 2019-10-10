package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;

	@ManyToMany(mappedBy = "notes")
	private List<Menu> menu;

	@ManyToOne
	@JoinColumn(name = "ordered_id", nullable = false)
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

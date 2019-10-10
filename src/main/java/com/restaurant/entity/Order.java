package com.restaurant.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private double completePrice;
	private LocalDateTime date;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pizza> pizzas;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PizzaAdd> pizzaAdds;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MainCourse> mainCourses;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MainCourseAdd> mainCourseAdds;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Soup> soups;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Drink> drinks;

	@OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Note> notes;
}

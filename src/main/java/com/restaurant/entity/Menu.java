package com.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private boolean actual = false;

	@ManyToMany
	@JoinTable(name = "menus_pizzas", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizzas;

	@ManyToMany
	@JoinTable(name = "menus_pizza_adds", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "pizza_add_id"))
	private List<PizzaAdd> pizzaAdds;

	@ManyToMany
	@JoinTable(name = "menus_main_courses", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "main_course_id"))
	private List<MainCourse> mainCourses;

	@ManyToMany
	@JoinTable(name = "menus_main_course_adds", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "main_course_add_id"))
	private List<MainCourseAdd> mainCourseAdds;

	@ManyToMany
	@JoinTable(name = "menus_soups", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "soup_id"))
	private List<Soup> soups;

	@ManyToMany
	@JoinTable(name = "menus_drinks", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
	private List<Drink> drinks;

	@OneToOne(mappedBy = "menu")
	private Note note;
}

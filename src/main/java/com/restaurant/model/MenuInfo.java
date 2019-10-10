package com.restaurant.model;

import java.util.List;

import lombok.Data;

@Data
public class MenuInfo {

	private String name;
	private boolean actual = false;
	private List<PizzaInfo> pizzas;
	private List<PizzaAddInfo> pizzaAdds;
	private List<MainCourseInfo> mainCourses;
	private List<MainCourseAddInfo> mainCourseAdds;
	private List<SoupInfo> soups;
	private List<DrinkInfo> drinks;
	private List<NoteInfo> notes;
	private double totalPrice = 0.0;
	private String orderName;
}

package com.restaurant.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderInfo {

	private String name;
	private double completePrice;
	private LocalDateTime date;
	private long orderId;

	private List<PizzaInfo> pizzas;
	private List<PizzaAddInfo> pizzaAdds;
	private List<MainCourseInfo> mainCourses;
	private List<MainCourseAddInfo> mainCourseAdds;
	private List<SoupInfo> soups;
	private List<DrinkInfo> drinks;
	private NoteInfo note;
}

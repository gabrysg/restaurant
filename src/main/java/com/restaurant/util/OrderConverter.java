package com.restaurant.util;

import static com.restaurant.util.ConverterUtils.getStream;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurant.entity.Drink;
import com.restaurant.entity.MainCourse;
import com.restaurant.entity.MainCourseAdd;
import com.restaurant.entity.Note;
import com.restaurant.entity.Order;
import com.restaurant.entity.Pizza;
import com.restaurant.entity.PizzaAdd;
import com.restaurant.entity.Soup;
import com.restaurant.model.DrinkInfo;
import com.restaurant.model.MainCourseAddInfo;
import com.restaurant.model.MainCourseInfo;
import com.restaurant.model.NoteInfo;
import com.restaurant.model.OrderInfo;
import com.restaurant.model.PizzaAddInfo;
import com.restaurant.model.PizzaInfo;
import com.restaurant.model.SoupInfo;

@Component
public class OrderConverter {

	public Order convertToOrder(OrderInfo orderInfo) {
		if (orderInfo != null) {
			Order order = new Order();
			order.setName(orderInfo.getName());
			order.setCompletePrice(orderInfo.getCompletePrice());
			order.setDate(orderInfo.getDate());

			order.setPizzas(getPizzas(orderInfo.getPizzas(), order));
			order.setPizzaAdds(getPizzaAdds(orderInfo.getPizzaAdds(), order));
			order.setMainCourses(getMainCourses(orderInfo.getMainCourses(), order));
			order.setMainCourseAdds(getMainCourseAdds(orderInfo.getMainCourseAdds(), order));
			order.setSoups(getSoups(orderInfo.getSoups(), order));
			order.setDrinks(getDrinks(orderInfo.getDrinks(), order));
			order.setNotes(getNotes(orderInfo.getNotes(), order));
			return order;
		}
		return null;
	}

	private List<Pizza> getPizzas(List<PizzaInfo> pizzas, Order order) {
		return getStream(pizzas).map(p -> new Pizza(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<PizzaAdd> getPizzaAdds(List<PizzaAddInfo> pizzaAdds, Order order) {
		return getStream(pizzaAdds).map(p -> new PizzaAdd(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<MainCourse> getMainCourses(List<MainCourseInfo> mainCourses, Order order) {
		return getStream(mainCourses).map(p -> new MainCourse(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<MainCourseAdd> getMainCourseAdds(List<MainCourseAddInfo> mainCourseAdds, Order order) {
		return getStream(mainCourseAdds).map(p -> new MainCourseAdd(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<Soup> getSoups(List<SoupInfo> soups, Order order) {
		return getStream(soups).map(p -> new Soup(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<Drink> getDrinks(List<DrinkInfo> drinks, Order order) {
		return getStream(drinks).map(p -> new Drink(p.getName(), p.getPrice(), p.getAmount(), order))
				.collect(Collectors.toList());
	}

	private List<Note> getNotes(List<NoteInfo> notes, Order order) {
		return getStream(notes).map(n -> new Note(n.getDescription(), order)).collect(Collectors.toList());
	}

	public List<OrderInfo> convertToOrderInfos(List<Order> orders) {
		return getStream(orders).map(order -> convertToOrderInfo(order)).collect(Collectors.toList());
	}

	public OrderInfo convertToOrderInfo(Order order) {
		if (order != null) {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setName(order.getName());
			orderInfo.setCompletePrice(order.getCompletePrice());
			orderInfo.setDate(order.getDate());
			orderInfo.setOrderId(order.getId());

			orderInfo.setPizzas(getPizzaInfos(order.getPizzas()));
			orderInfo.setPizzaAdds(getPizzaAddInfos(order.getPizzaAdds()));
			orderInfo.setMainCourses(getMainCourseInfos(order.getMainCourses()));
			orderInfo.setMainCourseAdds(getMainCourseAddInfos(order.getMainCourseAdds()));
			orderInfo.setSoups(getSoupInfos(order.getSoups()));
			orderInfo.setDrinks(getDrinkInfos(order.getDrinks()));
			orderInfo.setNotes(getNoteInfos(order.getNotes()));
			return orderInfo;
		} else {
			return null;
		}
	}

	private List<PizzaInfo> getPizzaInfos(List<Pizza> pizzas) {
		return getStream(pizzas).map(p -> new PizzaInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<PizzaAddInfo> getPizzaAddInfos(List<PizzaAdd> pizzaAdds) {
		return getStream(pizzaAdds).map(p -> new PizzaAddInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<MainCourseInfo> getMainCourseInfos(List<MainCourse> mainCourses) {
		return getStream(mainCourses).map(p -> new MainCourseInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<MainCourseAddInfo> getMainCourseAddInfos(List<MainCourseAdd> mainCourseAdds) {
		return getStream(mainCourseAdds).map(p -> new MainCourseAddInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<SoupInfo> getSoupInfos(List<Soup> soups) {
		return getStream(soups).map(p -> new SoupInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<DrinkInfo> getDrinkInfos(List<Drink> drinks) {
		return getStream(drinks).map(p -> new DrinkInfo(p.getName(), p.getPrice(), p.getAmount()))
				.collect(Collectors.toList());
	}

	private List<NoteInfo> getNoteInfos(List<Note> notes) {
		return getStream(notes).map(n -> new NoteInfo(n.getDescription())).collect(Collectors.toList());
	}

}

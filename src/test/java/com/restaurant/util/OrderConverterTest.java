package com.restaurant.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.restaurant.entity.Drink;
import com.restaurant.entity.MainCourse;
import com.restaurant.entity.MainCourseAdd;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.Note;
import com.restaurant.entity.Order;
import com.restaurant.entity.Pizza;
import com.restaurant.entity.PizzaAdd;
import com.restaurant.entity.Soup;
import com.restaurant.model.DrinkInfo;
import com.restaurant.model.MainCourseAddInfo;
import com.restaurant.model.MainCourseInfo;
import com.restaurant.model.MenuItemInfo;
import com.restaurant.model.NoteInfo;
import com.restaurant.model.OrderInfo;
import com.restaurant.model.PizzaAddInfo;
import com.restaurant.model.PizzaInfo;
import com.restaurant.model.SoupInfo;

public class OrderConverterTest {

	private OrderConverter orderConverter = new OrderConverter();

	private String str = "2016-03-04 11:30";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

	@Before
	public void setup() {
	}

	@Test
	public void testConvertToOrderWithEmptyFields() {

		Order order = orderConverter.convertToOrder(new OrderInfo());

		assertNotNull(order);
		assertNull(order.getName());
		assertEquals(0.0, order.getCompletePrice(), 0);
		assertNull(order.getDate());
		assertNotNull(order.getPizzas());
		assertTrue(order.getPizzas().isEmpty());
		assertNotNull(order.getPizzaAdds());
		assertTrue(order.getPizzaAdds().isEmpty());
		assertNotNull(order.getMainCourses());
		assertTrue(order.getMainCourses().isEmpty());
		assertNotNull(order.getMainCourseAdds());
		assertTrue(order.getMainCourseAdds().isEmpty());
		assertNotNull(order.getSoups());
		assertTrue(order.getSoups().isEmpty());
		assertNotNull(order.getDrinks());
		assertTrue(order.getDrinks().isEmpty());
		assertNull(order.getNote());
	}

	@Test
	public void testConvertToOrderWithInitializedFields() {
		OrderInfo orderInfo = getInitializedOrderInfo();

		Order order = orderConverter.convertToOrder(orderInfo);

		assertNotNull(order);
		assertEquals(orderInfo.getName(), order.getName());
		assertEquals(orderInfo.getCompletePrice(), order.getCompletePrice(), 0);
		assertEquals(orderInfo.getDate(), order.getDate());
		assertNotNull(order.getPizzas());
		assertOrderCollection(order.getPizzas(), orderInfo.getPizzas());
		assertNotNull(order.getPizzaAdds());
		assertOrderCollection(order.getPizzaAdds(), orderInfo.getPizzaAdds());
		assertNotNull(order.getMainCourses());
		assertOrderCollection(order.getMainCourses(), orderInfo.getMainCourses());
		assertNotNull(order.getMainCourseAdds());
		assertOrderCollection(order.getMainCourseAdds(), orderInfo.getMainCourseAdds());
		assertNotNull(order.getSoups());
		assertOrderCollection(order.getSoups(), orderInfo.getSoups());
		assertNotNull(order.getDrinks());
		assertOrderCollection(order.getDrinks(), orderInfo.getDrinks());
		assertEquals(orderInfo.getNote().getDescription(), order.getNote().getDescription());
	}

	@Test
	public void testConvertToOrderWithNull() {
		Order order = orderConverter.convertToOrder(null);
		assertNull(order);
	}

	@Test
	public void testConvertToOrderInfoWithEmptyFields() {

		OrderInfo orderInfo = orderConverter.convertToOrderInfo(new Order());

		assertNotNull(orderInfo);
		assertNull(orderInfo.getName());
		assertEquals(0.0, orderInfo.getCompletePrice(), 0);
		assertNull(orderInfo.getDate());
		assertNotNull(orderInfo.getPizzas());
		assertTrue(orderInfo.getPizzas().isEmpty());
		assertNotNull(orderInfo.getPizzaAdds());
		assertTrue(orderInfo.getPizzaAdds().isEmpty());
		assertNotNull(orderInfo.getMainCourses());
		assertTrue(orderInfo.getMainCourses().isEmpty());
		assertNotNull(orderInfo.getMainCourseAdds());
		assertTrue(orderInfo.getMainCourseAdds().isEmpty());
		assertNotNull(orderInfo.getSoups());
		assertTrue(orderInfo.getSoups().isEmpty());
		assertNotNull(orderInfo.getDrinks());
		assertTrue(orderInfo.getDrinks().isEmpty());
		assertNull(orderInfo.getNote());
	}

	@Test
	public void testConvertToOrderInfoWithInitializedFields() {
		Order order = getInitializedOrder();

		OrderInfo orderInfo = orderConverter.convertToOrderInfo(order);

		assertNotNull(orderInfo);
		assertEquals(order.getName(), orderInfo.getName());
		assertEquals(order.getCompletePrice(), orderInfo.getCompletePrice(), 0);
		assertEquals(order.getDate(), orderInfo.getDate());
		assertNotNull(orderInfo.getPizzas());
		assertOrderInfoCollection(orderInfo.getPizzas(), order.getPizzas());
		assertNotNull(orderInfo.getPizzaAdds());
		assertOrderInfoCollection(orderInfo.getPizzaAdds(), order.getPizzaAdds());
		assertNotNull(orderInfo.getMainCourses());
		assertOrderInfoCollection(orderInfo.getMainCourses(), order.getMainCourses());
		assertNotNull(orderInfo.getMainCourseAdds());
		assertOrderInfoCollection(orderInfo.getMainCourseAdds(), order.getMainCourseAdds());
		assertNotNull(orderInfo.getSoups());
		assertOrderInfoCollection(orderInfo.getSoups(), order.getSoups());
		assertNotNull(orderInfo.getDrinks());
		assertOrderInfoCollection(orderInfo.getDrinks(), order.getDrinks());
		assertEquals(order.getNote().getDescription(), orderInfo.getNote().getDescription());
	}

	@Test
	public void testConvertToOrderInfoWithNull() {
		OrderInfo orderInfo = orderConverter.convertToOrderInfo(null);
		assertNull(orderInfo);
	}

	@SuppressWarnings("unused")
	private void assertOrderCollection(List<? extends MenuItem> menuItems, List<? extends MenuItemInfo> menuItemInfos) {
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem menuItem = menuItems.get(i);
			MenuItemInfo menuItemInfo = menuItemInfos.get(i);
			assertEquals(menuItemInfo.getName(), menuItem.getName());
			assertEquals(menuItemInfo.getPrice(), menuItem.getPrice(), 0);
			assertEquals(menuItemInfo.getAmount(), menuItem.getAmount());
		}
	}

	@SuppressWarnings("unused")
	private void assertOrderInfoCollection(List<? extends MenuItemInfo> menuItemsInfo,
			List<? extends MenuItem> menuItems) {
		for (int i = 0; i < menuItemsInfo.size(); i++) {
			MenuItemInfo menuItemInfo = menuItemsInfo.get(i);
			MenuItem menuItem = menuItems.get(i);

			assertEquals(menuItem.getName(), menuItemInfo.getName());
			assertEquals(menuItem.getPrice(), menuItemInfo.getPrice(), 0);
			assertEquals(menuItem.getAmount(), menuItemInfo.getAmount());
		}
	}

	private OrderInfo getInitializedOrderInfo() {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setName("OrderName");
		orderInfo.setCompletePrice(100.01);
		orderInfo.setDate(dateTime);
		List<PizzaInfo> pizzaInfos = new ArrayList<PizzaInfo>();
		pizzaInfos.add(new PizzaInfo("pizza1", 10.0, 1));
		pizzaInfos.add(new PizzaInfo("pizza2", 20.0, 2));
		orderInfo.setPizzas(pizzaInfos);
		List<PizzaAddInfo> pizzaAddInfos = new ArrayList<PizzaAddInfo>();
		pizzaAddInfos.add(new PizzaAddInfo("pizzaAdd1", 10.0, 1));
		pizzaAddInfos.add(new PizzaAddInfo("pizzaAdd2", 20.0, 2));
		orderInfo.setPizzaAdds(pizzaAddInfos);
		List<MainCourseInfo> mainCourseInfos = new ArrayList<MainCourseInfo>();
		mainCourseInfos.add(new MainCourseInfo("mainCourse1", 10.0, 1));
		mainCourseInfos.add(new MainCourseInfo("mainCourse2", 20.0, 2));
		orderInfo.setMainCourses(mainCourseInfos);
		List<MainCourseAddInfo> mainCourseAddInfos = new ArrayList<MainCourseAddInfo>();
		mainCourseAddInfos.add(new MainCourseAddInfo("mainCourseAdd1", 10.0, 1));
		mainCourseAddInfos.add(new MainCourseAddInfo("mainCourseAdd2", 20.0, 2));
		orderInfo.setMainCourseAdds(mainCourseAddInfos);
		List<SoupInfo> soupInfos = new ArrayList<SoupInfo>();
		soupInfos.add(new SoupInfo("soup1", 10.0, 1));
		soupInfos.add(new SoupInfo("soup2", 20.0, 2));
		orderInfo.setSoups(soupInfos);
		List<DrinkInfo> drinkInfos = new ArrayList<DrinkInfo>();
		drinkInfos.add(new DrinkInfo("drink1", 10.0, 1));
		drinkInfos.add(new DrinkInfo("drink2", 20.0, 2));
		orderInfo.setDrinks(drinkInfos);
		orderInfo.setNote(new NoteInfo("With onion"));

		return orderInfo;
	}

	private Order getInitializedOrder() {
		Order order = new Order();
		order.setName("OrderName");
		order.setCompletePrice(100.01);
		order.setDate(dateTime);
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("pizza1", 10.0, 1));
		pizzas.add(new Pizza("pizza2", 20.0, 2));
		order.setPizzas(pizzas);
		List<PizzaAdd> pizzaAdds = new ArrayList<PizzaAdd>();
		pizzaAdds.add(new PizzaAdd("pizzaAdd1", 10.0, 1));
		pizzaAdds.add(new PizzaAdd("pizzaAdd2", 20.0, 2));
		order.setPizzaAdds(pizzaAdds);
		List<MainCourse> mainCourses = new ArrayList<MainCourse>();
		mainCourses.add(new MainCourse("mainCourse1", 10.0, 1));
		mainCourses.add(new MainCourse("mainCourse2", 20.0, 2));
		order.setMainCourses(mainCourses);
		List<MainCourseAdd> mainCourseAdds = new ArrayList<MainCourseAdd>();
		mainCourseAdds.add(new MainCourseAdd("mainCourseAdd1", 10.0, 1));
		mainCourseAdds.add(new MainCourseAdd("mainCourseAdd2", 20.0, 2));
		order.setMainCourseAdds(mainCourseAdds);
		List<Soup> soups = new ArrayList<Soup>();
		soups.add(new Soup("soup1", 10.0, 1));
		soups.add(new Soup("soup2", 20.0, 2));
		order.setSoups(soups);
		List<Drink> drinks = new ArrayList<Drink>();
		drinks.add(new Drink("drink1", 10.0, 1));
		drinks.add(new Drink("drink2", 20.0, 2));
		order.setDrinks(drinks);
		order.setNote(new Note("With onion"));

		return order;
	}

}

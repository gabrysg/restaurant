package com.restaurant.util;

import static com.restaurant.util.ConverterUtils.getStream;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.restaurant.entity.Drink;
import com.restaurant.entity.MainCourse;
import com.restaurant.entity.MainCourseAdd;
import com.restaurant.entity.Menu;
import com.restaurant.entity.Note;
import com.restaurant.entity.Pizza;
import com.restaurant.entity.PizzaAdd;
import com.restaurant.entity.Soup;
import com.restaurant.model.DrinkInfo;
import com.restaurant.model.MainCourseAddInfo;
import com.restaurant.model.MainCourseInfo;
import com.restaurant.model.MenuInfo;
import com.restaurant.model.MenuItemInfo;
import com.restaurant.model.NoteInfo;
import com.restaurant.model.OrderInfo;
import com.restaurant.model.PizzaAddInfo;
import com.restaurant.model.PizzaInfo;
import com.restaurant.model.SoupInfo;

@Component
public class MenuConverter {

	public MenuInfo convertToMenuInfo(Menu menu) {
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setName(menu.getName());
		menuInfo.setActual(menu.isActual());
		menuInfo.setPizzas(getPizzaInfos(menu.getPizzas()));
		menuInfo.setPizzaAdds(getPizzaAddInfos(menu.getPizzaAdds()));
		menuInfo.setMainCourses(getMainCourseInfos(menu.getMainCourses()));
		menuInfo.setMainCourseAdds(getMainCourseAddInfos(menu.getMainCourseAdds()));
		menuInfo.setSoups(getSoupInfos(menu.getSoups()));
		menuInfo.setDrinks(getDrinkInfos(menu.getDrinks()));
		menuInfo.setNote(getNoteInfo(menu.getNote()));
		return menuInfo;
	}

	private List<PizzaInfo> getPizzaInfos(List<Pizza> pizzas) {
		return getStream(pizzas).map(p -> new PizzaInfo(p.getName(), p.getPrice(), 0)).collect(Collectors.toList());
	}

	private List<PizzaAddInfo> getPizzaAddInfos(List<PizzaAdd> pizzaAdds) {
		return getStream(pizzaAdds).map(pa -> new PizzaAddInfo(pa.getName(), pa.getPrice(), 0))
				.collect(Collectors.toList());
	}

	private List<MainCourseInfo> getMainCourseInfos(List<MainCourse> mainCourses) {
		return getStream(mainCourses).map(mc -> new MainCourseInfo(mc.getName(), mc.getPrice(), 0))
				.collect(Collectors.toList());
	}

	private List<MainCourseAddInfo> getMainCourseAddInfos(List<MainCourseAdd> mainCourseAdds) {
		return getStream(mainCourseAdds).map(mca -> new MainCourseAddInfo(mca.getName(), mca.getPrice(), 0))
				.collect(Collectors.toList());
	}

	private List<SoupInfo> getSoupInfos(List<Soup> soups) {
		return getStream(soups).map(s -> new SoupInfo(s.getName(), s.getPrice(), 0)).collect(Collectors.toList());
	}

	private List<DrinkInfo> getDrinkInfos(List<Drink> drinks) {
		return getStream(drinks).map(d -> new DrinkInfo(d.getName(), d.getPrice(), 0)).collect(Collectors.toList());
	}

	private NoteInfo getNoteInfo(Note note) {
		if (note != null) {
			return new NoteInfo(note.getDescription());
		} else {
			return new NoteInfo();
		}
	}

	public OrderInfo convertToOrderInfo(MenuInfo menuInfo) {
		OrderInfo orderInfo = new OrderInfo();

		orderInfo.setPizzas(getStream(menuInfo.getPizzas()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setPizzaAdds(
				getStream(menuInfo.getPizzaAdds()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setMainCourses(
				getStream(menuInfo.getMainCourses()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setMainCourseAdds(
				getStream(menuInfo.getMainCourseAdds()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setSoups(getStream(menuInfo.getSoups()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setDrinks(getStream(menuInfo.getDrinks()).filter(p -> p.isSelected()).collect(Collectors.toList()));
		orderInfo.setNote(menuInfo.getNote());

		orderInfo.setCompletePrice(getCompletePrice(orderInfo));
		return orderInfo;
	}

	public double getCompletePrice(OrderInfo orderInfo) {
		return getStream(orderInfo.getPizzas()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum()
				+ getStream(orderInfo.getPizzaAdds()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum()
				+ getStream(orderInfo.getMainCourses()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum()
				+ getStream(orderInfo.getMainCourseAdds()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum()
				+ getStream(orderInfo.getSoups()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum()
				+ getStream(orderInfo.getDrinks()).mapToDouble(p -> p.getPrice() * p.getAmount()).sum();
	}

	public MenuInfo convertToMenuInfoWithSelections(Menu menu, OrderInfo orderInfo) {
		MenuInfo menuInfo = convertToMenuInfo(menu);
		setSelections(menuInfo, orderInfo);
		menuInfo.setTotalPrice(orderInfo.getCompletePrice());
		return menuInfo;
	}

	private void setSelections(MenuInfo menuInfo, OrderInfo orderInfo) {

		List<PizzaInfo> menuPizzaInfos = menuInfo.getPizzas();
		List<PizzaInfo> orderPizzaInfo = orderInfo.getPizzas();
		if (!CollectionUtils.isEmpty(orderPizzaInfo)) {
			for (MenuItemInfo orderedItemInfo : orderPizzaInfo) {
				for (MenuItemInfo menuItemInfo : menuPizzaInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}

		List<PizzaAddInfo> menuPizzaAddInfos = menuInfo.getPizzaAdds();
		List<PizzaAddInfo> orderPizzaAddInfo = orderInfo.getPizzaAdds();
		if (!CollectionUtils.isEmpty(orderPizzaAddInfo)) {
			for (MenuItemInfo orderedItemInfo : orderPizzaAddInfo) {
				for (MenuItemInfo menuItemInfo : menuPizzaAddInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}

		List<MainCourseInfo> menuMainCourseInfos = menuInfo.getMainCourses();
		List<MainCourseInfo> orderMainCourseInfo = orderInfo.getMainCourses();
		if (!CollectionUtils.isEmpty(orderMainCourseInfo)) {
			for (MenuItemInfo orderedItemInfo : orderMainCourseInfo) {
				for (MenuItemInfo menuItemInfo : menuMainCourseInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}

		List<MainCourseAddInfo> menuMainCourseAddInfos = menuInfo.getMainCourseAdds();
		List<MainCourseAddInfo> orderMainCourseAddInfo = orderInfo.getMainCourseAdds();
		if (!CollectionUtils.isEmpty(orderMainCourseAddInfo)) {
			for (MenuItemInfo orderedItemInfo : orderMainCourseAddInfo) {
				for (MenuItemInfo menuItemInfo : menuMainCourseAddInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}

		List<SoupInfo> menuSoupInfos = menuInfo.getSoups();
		List<SoupInfo> orderSoupInfo = orderInfo.getSoups();
		if (!CollectionUtils.isEmpty(orderSoupInfo)) {
			for (MenuItemInfo orderedItemInfo : orderSoupInfo) {
				for (MenuItemInfo menuItemInfo : menuSoupInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}

		List<DrinkInfo> menuDrinkInfos = menuInfo.getDrinks();
		List<DrinkInfo> orderDrinkInfo = orderInfo.getDrinks();
		if (!CollectionUtils.isEmpty(orderDrinkInfo)) {
			for (MenuItemInfo orderedItemInfo : orderDrinkInfo) {
				for (MenuItemInfo menuItemInfo : menuDrinkInfos) {
					if (menuItemInfo.getName().equals(orderedItemInfo.getName())) {
						menuItemInfo.setAmount(orderedItemInfo.getAmount());
						menuItemInfo.setSelected(true);
					}
				}
			}
		}
	}

}

CREATE TABLE pizza(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double,);
	
CREATE TABLE pizza_add(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double);

CREATE TABLE main_course(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE main_course_add(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE soup(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE drink(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE note(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	description varchar(255));
	
CREATE TABLE menu(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255),
	actual BOOLEAN default false);
	
CREATE TABLE menus_pizzas(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	pizza_id BIGINT NOT NULL, FOREIGN KEY (pizza_id) REFERENCES pizza(id),
	CONSTRAINT menu_pizza_constraint UNIQUE (menu_id, pizza_id));
	
CREATE TABLE menus_pizza_adds(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	pizza_add_id BIGINT NOT NULL, FOREIGN KEY (pizza_add_id) REFERENCES pizza_add(id),
	CONSTRAINT menu_pizza_add_constraint UNIQUE (menu_id, pizza_add_id));
	
CREATE TABLE menus_main_courses(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	main_course_id BIGINT NOT NULL, FOREIGN KEY (main_course_id) REFERENCES main_course(id),
	CONSTRAINT menu_main_course_constraint UNIQUE (menu_id, main_course_id));
	
CREATE TABLE menus_main_course_adds(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	main_course_add_id BIGINT NOT NULL, FOREIGN KEY (main_course_add_id) REFERENCES main_course_add(id),
	CONSTRAINT menu_main_course_add_constraint UNIQUE (menu_id, main_course_add_id));
	
CREATE TABLE menus_soups(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	soup_id BIGINT NOT NULL, FOREIGN KEY (soup_id) REFERENCES soup(id),
	CONSTRAINT menu_soup_constraint UNIQUE (menu_id, soup_id));
	
CREATE TABLE menus_drinks(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	drink_id BIGINT NOT NULL, FOREIGN KEY (drink_id) REFERENCES drink(id),
	CONSTRAINT menu_drink_constraint UNIQUE (menu_id, drink_id));
	
CREATE TABLE menus_notes(
	menu_id BIGINT NOT NULL, FOREIGN KEY (menu_id) REFERENCES menu(id),
	note_id BIGINT NOT NULL, FOREIGN KEY (note_id) REFERENCES note(id),
	CONSTRAINT menu_note_constraint UNIQUE (menu_id, note_id));
	
CREATE TABLE order_table(
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name varchar(255) NOT NULL,
	completePrice double);
	
CREATE TABLE order_table_pizzas(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	pizza_id BIGINT NOT NULL, FOREIGN KEY (pizza_id) REFERENCES pizza(id));
	
CREATE TABLE order_table_pizza_adds(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	pizza_add_id BIGINT NOT NULL, FOREIGN KEY (pizza_add_id) REFERENCES pizza_add(id));
	
CREATE TABLE order_table_main_couses(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	main_course_id BIGINT NOT NULL, FOREIGN KEY (main_course_id) REFERENCES main_course(id));
	
CREATE TABLE order_table_main_couse_adds(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	main_course_add_id BIGINT NOT NULL, FOREIGN KEY (main_course_add_id) REFERENCES main_course_add(id));
	
CREATE TABLE order_table_soups(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	soup_id BIGINT NOT NULL, FOREIGN KEY (soup_id) REFERENCES soup(id));
	
CREATE TABLE order_table_drinks(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	drink_id BIGINT NOT NULL, FOREIGN KEY (drink_id) REFERENCES drink(id));
	
CREATE TABLE order_table_notes(
	order_table_id BIGINT NOT NULL, FOREIGN KEY (order_table_id) REFERENCES order_table(id),
	note_id BIGINT NOT NULL, FOREIGN KEY (note_id) REFERENCES note(id));
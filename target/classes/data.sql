INSERT INTO pizza(name, price) 
VALUES ('Margheritta', 20), ('Vegetariana', 22), ('Tosca', 25), ('Venecia', 25);

INSERT INTO pizza_add(name, price) 
VALUES ('Podwójny ser', 2), ('Salami', 2), ('Szynka', 2), ('Pieczarki', 2);

INSERT INTO main_course(name, price) 
VALUES ('Schabowy z frytkami/ryżem/ziemniakami', 30), ('Ryba z frytkami', 28), ('Placek po węgiersku', 27);

INSERT INTO main_course_add(name, price) 
VALUES ('Bar sałatkowy', 5), ('Zestaw sosów', 6);

INSERT INTO soup(name, price) 
VALUES ('Pomidorowa', 12), ('Rosół', 10);

INSERT INTO drink(name, price) 
VALUES ('Kawa', 5), ('Herbata', 5), ('Cola', 5);

INSERT INTO menu(name, actual)
VALUES ('Menu główne', true);

INSERT INTO menus_pizzas(menu_id, pizza_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4);

INSERT INTO menus_pizza_adds(menu_id, pizza_add_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4);

INSERT INTO menus_main_courses(menu_id, main_course_id)
VALUES (1, 1), (1, 2), (1, 3);

INSERT INTO menus_main_course_adds(menu_id, main_course_add_id)
VALUES (1, 1), (1, 2);

INSERT INTO menus_soups(menu_id, soup_id)
VALUES (1, 1), (1, 2);

INSERT INTO menus_drinks(menu_id, drink_id)
VALUES (1, 1), (1, 2), (1, 3);


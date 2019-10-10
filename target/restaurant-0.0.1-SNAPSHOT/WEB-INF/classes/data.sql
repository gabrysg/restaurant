INSERT INTO pizza(id, name, price) 
VALUES (1, 'Margheritta', 20), (2, 'Vegetariana', 22), (3, 'Tosca', 25), (4, 'Venecia', 25);

INSERT INTO pizza_add(id, name, price) 
VALUES (1, 'Podwójny ser', 2), (2, 'Salami', 2), (3, 'Szynka', 2), (4, 'Pieczarki', 2);

INSERT INTO main_course(id, name, price) 
VALUES (1, 'Schabowy z frytkami/ryżem/ziemniakami', 30), (2, 'Ryba z frytkami', 28), (3, 'Placek po węgiersku', 27);

INSERT INTO main_course_add(id, name, price) 
VALUES (1, 'Bar sałatkowy', 5), (2,'Zestaw sosów', 6);

INSERT INTO soup(id, name, price) 
VALUES (1, 'Pomidorowa', 12), (2, 'Rosół', 10);

INSERT INTO drink(id, name, price) 
VALUES (1, 'Kawa', 5), (2, 'Herbata', 5), (3, 'Cola', 5);

INSERT INTO menu(id, name, actual)
VALUES (1, 'Menu główne', true);

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


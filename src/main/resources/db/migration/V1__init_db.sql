CREATE TABLE pizza (
	id INT IDENTITY NOT NULL,
	name varchar(255) NOT NULL,
	price double,
	PRIMARY KEY (id));
	
CREATE TABLE pizza_add (
	id INT NOT NULL,
	name varchar(255) NOT NULL,
	price double);

CREATE TABLE main_course (
	id INT NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE main_course_add (
	id INT NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE soup (
	id INT NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE drink (
	id INT NOT NULL,
	name varchar(255) NOT NULL,
	price double);
	
CREATE TABLE note (
	id INT NOT NULL,
	description varchar(255));

-- inserts

INSERT INTO pizza (name, price) 
VALUES ('Margheritta', 20), ('Vegetariana', 22), ('Tosca', 25), ('Venecia', 25);
package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	Menu findOneByActualTrue();

}

package com.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}

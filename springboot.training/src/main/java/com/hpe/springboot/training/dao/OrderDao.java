package com.hpe.springboot.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.training.entity.Order;

public interface OrderDao extends CrudRepository<Order, Integer> {

}

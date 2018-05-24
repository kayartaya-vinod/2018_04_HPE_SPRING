package com.hpe.springboot.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.training.entity.Category;

public interface CategoryDao extends CrudRepository<Category, Integer> {
}

package com.hpe.springboot.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.training.entity.Brand;

public interface BrandDao extends CrudRepository<Brand, Integer> {
}

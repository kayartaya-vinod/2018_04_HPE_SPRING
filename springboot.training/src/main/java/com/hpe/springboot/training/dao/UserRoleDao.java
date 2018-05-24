package com.hpe.springboot.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.training.entity.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, Integer> {

}

package com.hpe.springboot.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hpe.springboot.training.entity.Brand;
import com.hpe.springboot.training.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	// this is not required, since brand.getProducts() returns a Set<Product>
	public List<Product> findByBrand(Brand brand);

	// custom finders

	@Query("from Product where unitPrice between ? and ?")
	public List<Product> getProductsByPriceRange(Double min, Double max);

	@Query("from Product where lower(description) like '%?%'")
	public List<Product> searchByDescription(String keyword);
}

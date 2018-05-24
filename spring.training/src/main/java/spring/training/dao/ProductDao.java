package spring.training.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.training.entity.Product;

@Transactional(readOnly = true)
public interface ProductDao {

	// crud operations
	@Transactional(readOnly = false)
	public void addNewProduct(Product product) throws DaoException;

	public Product getById(Integer id) throws DaoException;

	@Transactional(readOnly = false, propagation=Propagation.MANDATORY)
	public void updateProduct(Product product) throws DaoException;

	@Transactional(readOnly = false)
	public void deleteProduct(Integer id) throws DaoException;

	// queries

	public List<Product> getMany(Integer pageNum, Integer pageSize) throws DaoException;

	public List<Product> getByPriceRange(Double min, Double max) throws DaoException;

	public int getProductCount() throws DaoException;
}

package spring.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

@Service
public class ProductManager {

	@Autowired
	@Qualifier("htDao")
	ProductDao dao;

	@Transactional(readOnly = false)
	public void updateProduct(Product product) throws DaoException {
		dao.updateProduct(product);
	}

	@Transactional(readOnly = false, rollbackFor = { DaoException.class })
	public void updateProductPricesFor(Integer... ids) throws DaoException {
		for (Integer id : ids) {
			Product p = dao.getById(id);
			p.setUnitPrice(p.getUnitPrice() - 10);
			dao.updateProduct(p);
		}
	}
}

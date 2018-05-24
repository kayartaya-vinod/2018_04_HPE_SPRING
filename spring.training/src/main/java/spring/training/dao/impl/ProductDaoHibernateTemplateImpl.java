package spring.training.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

@Repository("htDao")
public class ProductDaoHibernateTemplateImpl implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addNewProduct(Product product) throws DaoException {
		template.persist(product);
	}

	@Override
	public Product getById(Integer id) throws DaoException {
		return template.get(Product.class, id);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		if(product.getUnitPrice()<0) {
			throw new DaoException("Product price can not be negative");
		}
		template.update(product);
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		Product p = getById(id);
		if (p == null) {
			throw new DaoException("Invalid id!");
		}
		template.delete(p);
	}

	@Override
	public List<Product> getMany(Integer pageNum, Integer pageSize) throws DaoException {
		
		if(pageNum==null) pageNum = 1;
		if(pageSize==null) pageSize = 10;
		
		Session session = template.getSessionFactory().openSession();
		Query<Product> qry = session.createQuery("from Product", Product.class);
		qry.setFirstResult((pageNum - 1) * pageSize);
		qry.setMaxResults(pageSize);
		List<Product> list = qry.list();
		session.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getByPriceRange(Double min, Double max) throws DaoException {
		// String sql = "select * from products where unit_price between ? and ?";
		String hql = "from Product where unitPrice between ? and ?";
		return (List<Product>) template.find(hql, min, max);
	}

	@Override
	public int getProductCount() throws DaoException {
		String hql = "select count(p) from Product p";
		return Integer.parseInt(template.find(hql).get(0).toString());
	}

}






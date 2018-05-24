package spring.training.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

@Repository("dao")
public class ProductDaoJdbcTemplateImpl implements ProductDao {

	@Autowired(required = false)
	private JdbcTemplate template;

	public ProductDaoJdbcTemplateImpl() {
	}

	public ProductDaoJdbcTemplateImpl(JdbcTemplate template) {
		this.template = template;
	}

	// property name for XML property injection - "jdbcTemplate"
	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void addNewProduct(Product p) throws DaoException {
		String sql = "insert into products(name, category_id, brand_id, description, "
				+ "quantity_per_unit, unit_price, picture, discount) values(?,?,?,?,?,?,?,?)";
		template.update(sql, p.getName(), p.getCategoryId(), p.getBrandId(), p.getDescription(), p.getQuantityPerUnit(),
				p.getUnitPrice(), p.getPicture(), p.getDiscount());
	}

	@Override
	public void updateProduct(Product p) throws DaoException {
		String sql = "update products set name=?, category_id=?, brand_id=?, description = ?, "
				+ "quantity_per_unit=?, unit_price = ?, picture=?, discount=? where id = ?";
		template.update(sql, p.getName(), p.getCategoryId(), p.getBrandId(), p.getDescription(), p.getQuantityPerUnit(),
				p.getUnitPrice(), p.getPicture(), p.getDiscount(), p.getId());

	}

	@Override
	public Product getById(Integer id) throws DaoException {
		return template.queryForObject("select * from products where id = ?", new ProductRowMapper(), id);
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		int count = template.update("delete from products where id = ?", id);
		if (count == 0) {
			throw new DaoException("Invalid id for deletion!");
		}
	}

	@Override
	public List<Product> getMany(Integer pageNum, Integer pageSize) throws DaoException {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		int offset = (pageNum - 1) * pageSize;

		return template.query("select * from products limit ?, ?", new ProductRowMapper(), offset, pageSize);
	}

	@Override
	public List<Product> getByPriceRange(Double min, Double max) throws DaoException {
		return template.query("select * from products where unit_price between ? and ?", new ProductRowMapper(), min,
				max);
	}

	@Override
	public int getProductCount() throws DaoException {
		return template.queryForObject("select count(*) from products", Integer.class);
	}

	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
			p.setUnitPrice(rs.getDouble("unit_price"));
			p.setDiscount(rs.getDouble("discount"));
			p.setCategoryId(rs.getInt("category_id"));
			p.setBrandId(rs.getInt("brand_id"));
			return p;
		}
	}

}

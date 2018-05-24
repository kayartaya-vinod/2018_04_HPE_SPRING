package spring.training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

@Component("productDao")
public class ProductDaoJdbcImpl implements ProductDao {

	// fields
	private String driver;
	private String url;
	private String username;
	private String password;

	private Connection connection;

	@Qualifier("ds1")
	@Autowired(required = false)
	private DataSource dataSource; // connection pool

	public ProductDaoJdbcImpl() {
		System.out.println("ProductDaoJdbcImpl() called..");
	}

	public ProductDaoJdbcImpl(String driver, String url, String username, String password) {
		System.out.println("ProductDaoJdbcImpl(..) called...");
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	// this is a writable property called "driver" (mutator)
	// spring can use this for DI (property/setter based injection)
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// this exposes a writable property called "connection" of type
	// "java.sql.Connection"
	// Spring can use this for dependency injection via setter injection
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private Connection makeConnection() throws ClassNotFoundException, SQLException {
		if (dataSource != null)
			return dataSource.getConnection();

		if (connection != null)
			return connection;

		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	public int getProductCount() throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = makeConnection();
			stmt = conn.prepareStatement("select count(*) from products");
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return 0;
	}

	@Override
	public void addNewProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public Product getById(Integer id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getMany(Integer pageNum, Integer pageSize) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getByPriceRange(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

}

package spring.training.cfg;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.training.dao.ProductDao;
import spring.training.dao.impl.ProductDaoJdbcImpl;

// equivalent of <beans>..</beans>
@Configuration
public class AppConfig1 {

	@Bean
	public Connection conn1() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/northwind";
		String username = "root";
		String password = "root";
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
	@Bean
	public Connection conn2() throws Exception {
		String driver = "org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/hpe_spring_training";
		String username = "root";
		String password = "";
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	// @Lazy(true) // needed only for singleton
	@Scope("prototype")
	@Bean(name = { "productDao", "dao", "dao1" }, autowire=Autowire.BY_NAME)
	public ProductDao dao1() throws Exception {
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		// dao.setConnection(conn1()); // manual wiring
		return dao;
	}

}









package spring.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"spring.training.dao"})
public class AppConfig2 {

	@Bean
	public DataSource ds1() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/northwind");
		ds.setUsername("root");
		ds.setPassword("root");
		
		ds.setInitialSize(10);
		ds.setMaxIdle(10);
		ds.setMaxTotal(100);
		ds.setMinIdle(5);
		
		return ds;
	}
	
	@Bean
	public DataSource ds2() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost/~/hpe_spring_training");
		ds.setUsername("root");
		ds.setPassword("");
		
		ds.setInitialSize(10);
		ds.setMaxIdle(10);
		ds.setMaxTotal(100);
		ds.setMinIdle(5);
		
		return ds;
	}
}

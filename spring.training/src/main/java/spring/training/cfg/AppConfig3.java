package spring.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "spring.training.dao.impl", "spring.training.aop" })
@Configuration
public class AppConfig3 {
	
	@Bean
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public JdbcTemplate jt(DataSource dataSource) {
		return new JdbcTemplate(dataSource); // has inherited setter called "setDataSource"
	}

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

	@Primary
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

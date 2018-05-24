package spring.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.training.entity.Product;

@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "spring.training.dao.impl", "spring.training.service", "spring.training.aop" })
@Configuration
@EnableTransactionManagement
public class AppConfig4 {
	
	// create a bean of type PlatformTransactionManager
	@Bean
	public PlatformTransactionManager txManager(SessionFactory factory) {
		return new HibernateTransactionManager(factory);
	}

	@Bean // (autowire=Autowire.BY_NAME) // for sessionFactory
	public HibernateTemplate template() {
		HibernateTemplate ht = new HibernateTemplate();
		
		ht.setSessionFactory(sessionFactory().getObject()); // manual wiring
		
		return ht;
	}
	
	@Bean // (autowire = Autowire.BY_NAME) // for dataSource (db info)
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setAnnotatedClasses(Product.class); // mapping info
		
		lsfb.setDataSource(dataSource()); // manual wiring
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "false");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		lsfb.setHibernateProperties(props);
		return lsfb;
	}


	@Bean(name = "dataSource")
	public DataSource dataSource() {
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

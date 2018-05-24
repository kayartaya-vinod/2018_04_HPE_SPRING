package spring.training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.training.dao.ProductDao;

public class P01_SpringAsFactory {
	
	public static void main(String[] args) throws Exception {
		
		// variable representing the spring container
		ApplicationContext ctx;
		
		// spring container created using an XML file found in the classpath
		ctx = new ClassPathXmlApplicationContext("context.xml");
		
		ProductDao dao = ctx.getBean("dao1", ProductDao.class);
		int pc = dao.getProductCount();
		System.out.println("Product count = " + pc);
		
		((AbstractApplicationContext) ctx).close();
		
	}

}

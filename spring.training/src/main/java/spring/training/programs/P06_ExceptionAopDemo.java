package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig4;
import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

public class P06_ExceptionAopDemo {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());
		
		Product p1 = new Product();
		try {
			dao.addNewProduct(p1);
		} catch (DaoException e) {
			System.out.println("Received DaoException: " + e);
		} catch(Exception e) {
			System.out.println("Exception received of type: " + e.getClass());
		}
		
		ctx.close();
	}

}

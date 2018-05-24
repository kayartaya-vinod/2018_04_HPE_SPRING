package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig2;
import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;

public class P02_AnnocationConfigDemo {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig2.class);
		ProductDao dao = ctx.getBean("productDao", ProductDao.class);
		System.out.println("Product count = " + dao.getProductCount());
		ctx.close();
		
	}
}

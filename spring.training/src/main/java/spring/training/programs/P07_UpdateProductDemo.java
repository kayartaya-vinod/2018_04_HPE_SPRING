package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig4;
import spring.training.dao.DaoException;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;
import spring.training.service.ProductManager;

public class P07_UpdateProductDemo {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);

		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		ProductManager mgr = ctx.getBean(ProductManager.class);
		
		System.out.println("dao is an instanceof " + dao.getClass().getName());
		Product p1 = dao.getById(11);
		System.out.println(p1);
		p1.setUnitPrice(p1.getUnitPrice() + 1);
		
		// dao.updateProduct(p1); // causes error
		mgr.updateProduct(p1);
		
		p1 = dao.getById(11);
		System.out.println(p1);

		ctx.close();

	}

}

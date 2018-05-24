package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig4;
import spring.training.dao.DaoException;
import spring.training.service.ProductManager;

public class P08_TransactionDemo {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);

		ProductManager mgr = ctx.getBean(ProductManager.class);
		
		mgr.updateProductPricesFor(23, 27, 28, 30, 32);

		ctx.close();

	}

}

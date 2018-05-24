package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig4;
import spring.training.dao.ProductDao;
import spring.training.entity.Product;

public class P04_TestingProductDaoJdbcTemplateImpl {

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		ProductDao dao = ctx.getBean("htDao", ProductDao.class);

		System.out.println("dao is an instanceof " + dao.getClass());

		Product p1 = dao.getById(11);
		System.out.println(p1);

		// p1.setUnitPrice(p1.getUnitPrice() + 1);
		// dao.updateProduct(p1);
		// p1 = dao.getById(11);
		// System.out.println(p1);

		System.out.println("-----------");
		List<Product> list = dao.getMany(2, null);
		for (Product p : list) {
			System.out.println(p);
		}
		System.out.println("-----------");
		list = dao.getByPriceRange(50.0, 40.0);
		for (Product p : list) {
			System.out.println(p);
		}

		ctx.close();
	}

}

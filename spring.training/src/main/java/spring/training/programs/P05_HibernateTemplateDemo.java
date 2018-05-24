package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import spring.training.cfg.AppConfig4;
import spring.training.entity.Product;

public class P05_HibernateTemplateDemo {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig4.class);
		
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		
		Product p = ht.get(Product.class, 11);

		System.out.println(p);
		
		
		
		ctx.close();
		
		
	}
}

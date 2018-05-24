package spring.training.programs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import spring.training.cfg.AppConfig3;
import spring.training.entity.Product;

public class P03_JdbcTemplateDemo {
	
	static JdbcTemplate template;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig3.class);
		template = ctx.getBean(JdbcTemplate.class);
		
		// printProductCount();
		// printProductName(11); // 11 --> product id
		// printProductDetails(11); // 11 --> product id
		// printProductsPricedBetween(10.0, 15.0); // min_price = 10, max_price = 15
		
		getProductById(11);
		ctx.close();
	}
	
	static class ProductRowMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
			p.setUnitPrice(rs.getDouble("unit_price"));
			p.setDiscount(rs.getDouble("discount"));
			p.setCategoryId(rs.getInt("category_id"));
			p.setBrandId(rs.getInt("brand_id"));
			return p;
		}
		
	}

	private static void getProductById(int id) {
		Product p = template.queryForObject("select * from products where id = ?", 
					new ProductRowMapper(), id);
		System.out.println(p);
	}

	static void printProductsPricedBetween(double min, double max) {
		String sql = "select * from products where unit_price between ? and ?";
		List<Map<String, Object>> list = template.queryForList(sql, min, max);
		for(Map<String, Object> map: list) {
			System.out.println(map.get("name") + " --> Rs." + map.get("unit_price"));
		}
	}

	static void printProductDetails(int id) {
		// 1 row multiple columns
		String sql = "select * from products where id = ?";
		Map<String, Object> map = template.queryForMap(sql, id);
		System.out.println("Name = " + map.get("name"));
		System.out.println("Price = " + map.get("unit_price"));
		System.out.println("Desc = " + map.get("descrIPTION"));
		System.out.println(map);
	}

	static void printProductName(int id) {
		String sql = "select name from products where id = ?";
		String pname = template.queryForObject(sql, String.class, id);
		System.out.println("Name of the product is " + pname);
	}

	static void printProductCount() {
		// use queryForObject when the SQL returns 1 row 1 column
		int pc = template.queryForObject("select count(*) from products", Integer.class);
		System.out.println("product count = " + pc);
	}
}







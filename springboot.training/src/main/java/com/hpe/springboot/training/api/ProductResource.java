package com.hpe.springboot.training.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.springboot.training.dao.ProductDao;
import com.hpe.springboot.training.entity.Product;
import com.hpe.springboot.training.entity.ProductList;
import com.hpe.springboot.training.entity.Version;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
	ProductDao prDao;

	@RequestMapping(value = "/version", produces = { "application/json", "application/xml" })
	public Version versionAsJson() {
		return new Version("1.0.1");
	}

	@RequestMapping(value = "/version", produces = "text/plain")
	public String version() {
		return "version: 1.0";
	}
	
	@RequestMapping(value="/{product_id}", method=RequestMethod.GET,
			produces= {"application/json", "application/xml"})
	public Product getById(@PathVariable("product_id") Integer id) {
		return prDao.findById(id).get();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Iterable<Product> getAllProductsAsJson() {
		return prDao.findAll();
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/xml")
	public ProductList getAllProductsAsXml() {
		List<Product> list = new ArrayList<>();
		Iterator<Product> it = prDao.findAll().iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return new ProductList(list);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json", 
			consumes= {"application/json", "application/xml"})
	public Map<String, Object> addNewProduct(@RequestBody Product product) {
		
		Map<String, Object> resp = new HashMap<>();
		try {
			prDao.save(product);
			resp.put("success", true);
			resp.put("id", product.getId());
		} catch (Exception e) {
			resp.put("success", false);
			resp.put("message", e.getMessage());
		}
		return resp;
	}
}













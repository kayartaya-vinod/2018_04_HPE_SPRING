package com.hpe.springboot.training.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpe.springboot.training.dao.BrandDao;
import com.hpe.springboot.training.dao.CategoryDao;
import com.hpe.springboot.training.dao.CustomerDao;
import com.hpe.springboot.training.dao.OrderDao;
import com.hpe.springboot.training.dao.ProductDao;
import com.hpe.springboot.training.entity.Brand;
import com.hpe.springboot.training.entity.Category;
import com.hpe.springboot.training.entity.Customer;
import com.hpe.springboot.training.entity.LineItem;
import com.hpe.springboot.training.entity.Order;
import com.hpe.springboot.training.entity.Product;

@SuppressWarnings("unchecked")
@Controller
public class ProductController {
	
	@Autowired
	ProductDao dao;
	
	@Autowired
	BrandDao bdao;
	
	@Autowired
	CategoryDao cdao;
	
	@ModelAttribute("brands")
	public Iterable<Brand> brands() {
		return bdao.findAll();
	}

	@ModelAttribute("categories")
	public Iterable<Category> categories() {
		return cdao.findAll();
	}
	
	@Autowired
	OrderDao odao;
	
	@Autowired
	CustomerDao custDao;
	
	@RequestMapping("/checkout")
	public String checkout(Principal principal, HttpSession session) {
		
		String email = principal.getName(); // logged-in username (email, in our case)
		Customer customer = custDao.findByEmail(email);
		
		Order order = new Order();
		order.setCustomer(customer);
		
		Map<Integer, LineItem> cart = (Map<Integer, LineItem>) session.getAttribute("cart");
		if(cart!=null) {
			order.addLineItems(cart.values());
		}
		
		odao.save(order);
		session.removeAttribute("cart");
		
		// generate the payment page using the gateway api, and 
		// redidect to that page
		
		return "redirect:/view-cart";
	}
	
	@RequestMapping("/remove-from-cart")
	public String removeFromCart(@RequestParam("id") Integer productId, HttpSession session) {
		Map<Integer, LineItem> cart = (Map<Integer, LineItem>) session.getAttribute("cart");
		if(cart!=null) {
			if(cart.containsKey(productId)) {
				cart.remove(productId);
			}
		}
		return "redirect:/view-cart";
	}

	
	@RequestMapping("/add-to-cart")
	public String addToCart(
			@RequestParam("product_id") Integer productId, 
			@RequestParam Integer quantity, 
			HttpSession session) {
		
		Product p = dao.findById(productId).get();
		LineItem item = new LineItem();
		item.setProduct(p);
		item.setQuantity(quantity);
		item.setUnitPrice(p.getUnitPrice());	
		
		Map<Integer, LineItem> cart = (Map<Integer, LineItem>) session.getAttribute("cart");
		if(cart==null) {
			cart = new HashMap<Integer, LineItem>();
			session.setAttribute("cart", cart);
		}
		
		if (cart.containsKey(p.getId())) {
			LineItem li = cart.get(p.getId());
			li.setQuantity(li.getQuantity() + quantity);
		} else {
			cart.put(p.getId(), item);
		}

		return "redirect:/home";
	}
	
	@RequestMapping("/view-cart")
	public String viewCart(Model model, HttpSession session) {
		Map<Integer, LineItem> cart = (Map<Integer, LineItem>) session.getAttribute("cart");
		if(cart!=null) {
			model.addAttribute("lineitems", cart.values()); // all line-item
		}
		return "show-cart";
	}
	
	@RequestMapping({"/", "/home"})
	public String home(
			@RequestParam(name="filter-by", required=false, defaultValue="") String filterBy,
			@RequestParam(name="id", required=false) Integer id,
			Model model) {
		
		Iterable<Product> list;
		switch(filterBy) {
		case "brand":
			Brand b = bdao.findById(id).get();
			list = b.getProducts();
			break;
		case "category":
			Category c = cdao.findById(id).get();
			list = c.getProducts();
			break;
		default:
			list = dao.findAll();
		}
		
		model.addAttribute("products", list); 
		
		return "home";
	}
	
	@RequestMapping("/view-all-products")
	public String getProductList(
			@RequestParam(name="page", required=false, defaultValue="0") Integer pageNum, 
			Model model) {
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		List<Product> list = dao.findAll(pageable).getContent();
		
		model.addAttribute("count", dao.count());
		model.addAttribute("products", list);
		
		
		return "product-list"; // logical view name
	}
	
}

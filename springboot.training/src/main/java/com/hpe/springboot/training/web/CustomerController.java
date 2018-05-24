package com.hpe.springboot.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hpe.springboot.training.dao.BrandDao;
import com.hpe.springboot.training.dao.CategoryDao;
import com.hpe.springboot.training.dao.CustomerDao;
import com.hpe.springboot.training.dao.ProductDao;
import com.hpe.springboot.training.dao.UserRoleDao;
import com.hpe.springboot.training.entity.Brand;
import com.hpe.springboot.training.entity.Category;
import com.hpe.springboot.training.entity.Customer;
import com.hpe.springboot.training.entity.Product;
import com.hpe.springboot.training.entity.UserRole;

@Controller
public class CustomerController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ProductDao pdao;

	@Autowired
	BrandDao bdao;

	@Autowired
	CategoryDao cdao;

	@Autowired
	CustomerDao dao;

	@Autowired
	CustomerValidator validator;

	@ModelAttribute("brands")
	public Iterable<Brand> brands() {
		return bdao.findAll();
	}

	@ModelAttribute("categories")
	public Iterable<Category> categories() {
		return cdao.findAll();
	}

	@ModelAttribute("products")
	public Iterable<Product> products() {
		return pdao.findAll();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String gotoRegistrationPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "registration-form";
	}
	
	@Autowired
	UserRoleDao urDao;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomer(@ModelAttribute Customer customer, Errors errors) {

		validator.validate(customer, errors);

		if (errors.hasErrors()) {
			System.out.println("There are some conversion errors");
			return "registration-form";
		}

		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		dao.save(customer);
		urDao.save(new UserRole(customer.getEmail()));
		
		return "redirect:/home"; // always a GET request
	}
}







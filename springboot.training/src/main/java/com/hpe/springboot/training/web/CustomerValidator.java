package com.hpe.springboot.training.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hpe.springboot.training.entity.Customer;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// all these are checked against the request parameters
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "customer.name.blank", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "customer.email.blank", "Email address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "customer.phone.blank", "Phone number is required");

		// check the customer's values
		Customer c = (Customer) obj;
		int len = c.getName().trim().length();

		if (len > 1 && len < 3) {
			errors.rejectValue("name", "customer.name.length", "Minimum 3 letters required");
		}
	}

}

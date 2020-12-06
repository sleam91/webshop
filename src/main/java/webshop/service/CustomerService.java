package webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import webshop.data.CustomerRepository;
import webshop.model.Customer;

@Service
@SessionScope
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	private Customer customer;

	public void login(String email) {
		customer = customerRepository.findByEmail(email).orElseThrow();
	}

	public void register(String email) {
		if (customerRepository.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Error, email already in use");
		}
		customerRepository.save(new Customer(email));
	}

	public Customer getCustomer() {
		return customer;
	}
	
	

}

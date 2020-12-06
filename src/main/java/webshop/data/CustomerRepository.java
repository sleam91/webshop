package webshop.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmail(String email); 
}

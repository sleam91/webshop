package webshop.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByNameContainingIgnoreCase(String searchQuery);
	List<Product> findByCategoryName(String category);
}

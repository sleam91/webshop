package webshop.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
	 
	Optional<Category> findByName(String category);
}

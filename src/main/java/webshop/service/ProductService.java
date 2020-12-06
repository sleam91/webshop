package webshop.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webshop.data.CategoryRepository;
import webshop.data.ProductRepository;
import webshop.model.Category;
import webshop.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Product> findProducts(String searchQuery) {
		return productRepository.findByNameContainingIgnoreCase(searchQuery);
	}

	public Product findById(int productId) {
		return productRepository.findById(productId).get();
	}

	public List<Product> findCategory(String category) {
		return productRepository.findByCategoryName(category);
	};

	public Iterator<String> getCategories() {
		return categoryRepository.findAll().stream()
				.sorted((c1, c2) -> Integer.compare(c1.getId(), c2.getId()))
				.map(Category::getName).iterator();
	}
}

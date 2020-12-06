package webshop.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webshop.data.CategoryRepository;
import webshop.data.OrderRepository;
import webshop.data.ProductRepository;
import webshop.model.Category;
import webshop.model.Order;
import webshop.model.Product;

@Service
public class AdminService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CategoryRepository categoryRepository;

	public void addNewProduct(Product product) {
		productRepository.save(product);
	}

	public List<Order> getOrders() {
		return orderRepository.findByOrderByDispatchedAsc();
	}

	public void dispatchOrder(int id) {
		Order dispatchedOrder = orderRepository.findById(id).orElseThrow();
		dispatchedOrder.setDispatched(true);
		orderRepository.save(dispatchedOrder);
	}

	public Iterator<Category> getCategories() {
		return categoryRepository.findAll().stream()
				.sorted((c1, c2) -> Integer.compare(c1.getId(), c2.getId()))
				.iterator();
	}

	public Category getCategory(String category) {
		return categoryRepository.findByName(category).orElseThrow();
	}
}

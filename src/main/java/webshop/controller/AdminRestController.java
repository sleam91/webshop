package webshop.controller;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webshop.model.Order;
import webshop.model.Product;
import webshop.service.AdminService;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {

	@Autowired
	AdminService adminService;

	@PostMapping("/products")
	public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
		product.setCategory(adminService.getCategory(product.getCategory().getName()));
		adminService.addNewProduct(product);
		return ResponseEntity.accepted().body("Product added!");
	}

	@GetMapping("/orders")
	public Stream<Order> getOrders() {
		return adminService.getOrders().stream();
	}

	@PostMapping("/orders")
	public ResponseEntity<String> distpatchOrder(@RequestParam int id) {
		try {
			adminService.dispatchOrder(id);
			return ResponseEntity.accepted().body("Order dispatched!");
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(404).body("Order does not exist!");
		}

	}

}

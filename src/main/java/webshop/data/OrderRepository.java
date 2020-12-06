package webshop.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByOrderByDispatchedAsc();
}

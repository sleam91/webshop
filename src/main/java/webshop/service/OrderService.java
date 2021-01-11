package webshop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import webshop.data.OrderRepository;
import webshop.model.Cart;
import webshop.model.Person;
import webshop.model.Order;
import webshop.model.OrderLine;
import webshop.model.Product;

@Service
@SessionScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private Cart cart = new Cart();

    public void placeOrder(Person person) {
	Order order = new Order();
	order.setDate(LocalDateTime.now());
	order.setOrderLines(cart.getOrderLines());
	order.setPerson(person);
	orderRepository.save(order);
    }

    public void resetCart() {
	cart.setOrderLines(new ArrayList<>());
    }

    public List<OrderLine> getCartContents() {
	return Collections.unmodifiableList(cart.getOrderLines());
    }

    public void addToCart(Product product) {
	cart.addToCart(product);
    }

    public void removeFromCart(Product product) {
	cart.removeFromCart(product);
    }

    public double getTotal() {
	return cart.getTotal();
    }

}

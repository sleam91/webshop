package webshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webshop.data.OrderRepository;
import webshop.model.Category;
import webshop.model.Customer;
import webshop.model.Order;
import webshop.model.Product;

class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderRepository orderRepository;

	private Customer customer = new Customer("test@test.com");
	private Order order = new Order();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(orderRepository.save(any())).thenReturn(order);
	}

	@Test
	void testPlaceOrder() {
		orderService.placeOrder(customer);
		verify(orderRepository).save(any());

	}

	@Test
	void testResetCart() {
		Product product1 = new Product();
		product1.setId(1);
		product1.setImg("placeholder.jpg");
		product1.setName("TestProduct1");
		product1.setPrice(19.99);
		product1.setCategory(new Category("TestCategory"));
		orderService.addToCart(product1);
		orderService.addToCart(product1);
		orderService.addToCart(product1);
		orderService.resetCart();
		assertEquals(0, orderService.getTotal());
	}

}

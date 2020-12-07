package webshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest {

    private Cart cart;
    private Product product1 = new Product();
    private Product product2 = new Product();

    @BeforeEach
    void setUp() {
	product1.setId(1);
	product1.setImg("placeholder.jpg");
	product1.setName("TestProduct1");
	product1.setPrice(19.99);
	product1.setCategory(new Category("TestCategory"));
	product1.setId(2);
	product2.setImg("placeholder.jpg");
	product2.setName("TestProduct2");
	product2.setPrice(32.49);
	product2.setCategory(new Category("TestCategory"));
	cart = new Cart();
    }

    @Test
    void testAddToCart1() {
	cart.addToCart(product1);
	assertEquals(product1.getName(), cart.getOrderLines().get(0).getProduct().getName());
    }

    @Test
    void testAddToCart2() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	assertEquals(2, cart.getOrderLines().get(0).getQuantity());
    }

    @Test
    void testAddToCart3() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product2);
	int totalQuantity = cart.getOrderLines().stream().map(OrderLine::getQuantity).reduce(0, Integer::sum);
	assertEquals(3, totalQuantity);
    }

    @Test
    void testRemoveFromCart1() {
	cart.addToCart(product1);
	cart.removeFromCart(product1);
	assertEquals(0, cart.getOrderLines().size());
    }

    @Test
    void testRemoveFromCart2() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product2);
	cart.removeFromCart(product1);
	assertEquals(2, cart.getOrderLines().size());
    }

    @Test
    void testRemoveFromCart3() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product2);
	cart.removeFromCart(product1);
	int totalQuantity = cart.getOrderLines().stream().map(OrderLine::getQuantity).reduce(0, Integer::sum);
	assertEquals(3, totalQuantity);
    }

    @Test
    void testGetTotal1() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product2);
	double totalExpected = roundDecimals(product1.getPrice() * 2 + product2.getPrice());
	assertEquals(totalExpected, cart.getTotal());
    }

    @Test
    void testGetTotal2() {
	cart.addToCart(product1);
	cart.addToCart(product1);
	cart.addToCart(product2);
	cart.removeFromCart(product2);
	cart.removeFromCart(product1);
	double totalExpected = roundDecimals(product1.getPrice());
	assertEquals(totalExpected, cart.getTotal());
    }

    private double roundDecimals(double number) {
	return Math.round(number * 100.0) / 100.0;
    }
}

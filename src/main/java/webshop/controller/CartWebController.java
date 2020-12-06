package webshop.controller;

import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webshop.model.Product;
import webshop.service.CustomerService;
import webshop.service.OrderService;
import webshop.service.ProductService;

@Controller
public class CartWebController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;

	@GetMapping("/cart")
	public String viewCart(Model m) {
		if (Optional.ofNullable(customerService.getCustomer()).isEmpty()) {
			return "redirect:/login";
		}
		m.addAttribute("cartTotal", orderService.getTotal());
		m.addAttribute("cart", orderService.getCartContents());
		return "cart";
	}

	@PostMapping("/cart/add")
	public String addToCart(int productId, int quantity, Model m) {
		if (Optional.ofNullable(customerService.getCustomer()).isEmpty()) {
			return "redirect:/login";
		}
		Product product = productService.findById(productId);
		IntStream.range(0, quantity == 0 ? 1 : quantity).forEach(i -> orderService.addToCart(product));

		return "redirect:/cart";
	}

	@PostMapping("/cart/remove")
	public String removeFromCart(int productId, Model m) {
		orderService.removeFromCart(productService.findById(productId));
		return "redirect:/cart";
	}

	@PostMapping("/cart/place-order")
	public String placeOrder(Model m, RedirectAttributes attributes) {
		if (Optional.ofNullable(customerService.getCustomer()).isEmpty()) {
			return "redirect:/login";
		}
		attributes.addFlashAttribute("orderPlaced", true);
		orderService.placeOrder(customerService.getCustomer());
		return "redirect:/confirmation";
	}

	@GetMapping("/confirmation")
	public String showConfirmation(Model m) {
		if (!m.containsAttribute("orderPlaced")) {
			return "redirect:/cart";
		}
		m.addAttribute("cart", orderService.getCartContents());
		m.addAttribute("cartTotal", orderService.getTotal());
		orderService.resetCart();

		return "confirmation";
	}

}

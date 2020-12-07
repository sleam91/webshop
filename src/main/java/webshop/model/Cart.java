package webshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderLine> orderLines = new ArrayList<>();

    public void addToCart(Product product) {
	if (!orderLines.stream().anyMatch(o -> o.getProduct().getId() == product.getId())) {
	    OrderLine orderLine = new OrderLine();
	    orderLine.setProduct(product);
	    orderLine.setQuantity(1);
	    orderLines.add(orderLine);
	} else {
	    OrderLine orderLine = orderLines.stream().filter(o -> o.getProduct().getId() == product.getId()).findFirst()
		    .get();
	    orderLine.setQuantity(orderLine.getQuantity() + 1);
	}
    }

    public void removeFromCart(Product product) {
	OrderLine orderLine = orderLines.stream().filter(o -> o.getProduct().getId() == product.getId()).findFirst()
		.get();
	if (orderLine.getQuantity() == 1) {
	    orderLines.remove(orderLine);
	} else {
	    orderLine.setQuantity(orderLine.getQuantity() - 1);

	}
    }

    public double getTotal() {
	return roundDecimals(orderLines.stream().map(o -> roundDecimals(o.getProduct().getPrice() * o.getQuantity()))
		.reduce(0d, Double::sum));
    }

    private double roundDecimals(double number) {
	return Math.round(number * 100.0) / 100.0;
    }

    public List<OrderLine> getOrderLines() {
	return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
	this.orderLines = orderLines;
    }
}

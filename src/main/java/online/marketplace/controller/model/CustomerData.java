package online.marketplace.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.marketplace.entity.Customer;
import online.marketplace.entity.Order;
import online.marketplace.entity.Product;

import java.util.Set;
import java.util.HashSet;


@Data
public class CustomerData {
	
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerEmail;
	private Set<OrderResponse> orders = new HashSet<>();
	
	public CustomerData() {
		
	}
	
	public CustomerData(Customer customer) {
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerAddress = customer.getCustomerAddress();
		customerPhoneNumber = customer.getCustomerPhoneNumber();
		customerEmail = customer.getCustomerEmail();
		
		for(Order order : customer.getOrders()) {
			
			orders.add(new OrderResponse(order));
		}
		
		
	}
	
	@Data
	@NoArgsConstructor
	static class OrderResponse{
		
		private Long orderId;
		
		private Set<String> products = new HashSet<>();
		
		OrderResponse(Order order) {
			orderId = order.getOrderId();
			
			for(Product product : order.getProducts()) {
				products.add(product.getProduct());

			}
		}
	}
}

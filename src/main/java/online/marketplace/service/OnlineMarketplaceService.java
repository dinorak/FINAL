package online.marketplace.service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.marketplace.controller.model.CustomerData;
import online.marketplace.controller.model.OrderData;
import online.marketplace.controller.model.ProductData;
import online.marketplace.entity.Customer;
import online.marketplace.entity.Order;
import online.marketplace.entity.Product;
import online.marketplace.repository.CustomerDao;
import online.marketplace.repository.OrderDao;
import online.marketplace.repository.ProductDao;
import java.util.*;
@Service
public class OnlineMarketplaceService {

	 private final CustomerDao customerDao;
	 private final OrderDao orderDao;
	 private final ProductDao productDao;
	
	
	@Autowired
    public OnlineMarketplaceService(CustomerDao customerDao, OrderDao orderDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
	}

	@Transactional(readOnly = false)
	public CustomerData insertCustomer(CustomerData customerData) {
	    Long customerId = customerData.getCustomerId();
	    Customer customer = findOrCreateCustomer(customerId);

	    setFieldsInCustomer(customer, customerData);
	    return new CustomerData(customerDao.save(customer));
	}

	private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
		customer.setCustomerFirstName(customerData.getCustomerFirstName());
		customer.setCustomerLastName(customerData.getCustomerLastName());
		customer.setCustomerAddress(customerData.getCustomerAddress());
		customer.setCustomerEmail(customerData.getCustomerEmail());
		customer.setCustomerPhoneNumber(customerData.getCustomerPhoneNumber());
	}

	private Customer findOrCreateCustomer(Long customerId) {
		Customer customer;
		if (Objects.isNull(customerId)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(customerId);
		}
		return customer;
	}

	public Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
	}

	public CustomerData editCustomer(Long custId, CustomerData customer) {
		Customer customerToUpdate = findCustomerById(custId);
		setFieldsInCustomer(customerToUpdate, customer);
		return new CustomerData(customerDao.save(customerToUpdate));
	}

	@Transactional(readOnly = false)
	public void deleteCustomer(Long customerId) {
		customerDao.deleteById(customerId);
	}
	
	public Product createProduct(ProductData productData) {
	    
	        Product product = new Product();
	        product.setProduct(productData.getProduct());
	        
	        productDao.save(product);
	        return product;
	    }

	public OrderData createOrder(OrderData orderData) {
		Long orderId = orderData.getOrderId();
		Order order = findOrCreateOrder(orderId);
		
		copyFieldsInOrder(order, orderData);
		return new OrderData(orderDao.save(order));
	}

	private void copyFieldsInOrder(Order order, OrderData orderData) {
		order.setCustomer(findOrCreateCustomer(orderData.getCustomerId()));
		
	}

	private Order findOrCreateOrder(Long orderId) {
		if (Objects.isNull(orderId)) {
			return new Order();
		}
		return findOrderById(orderId);
	}

	private Order findOrderById(Long orderId) {
		return orderDao.findById(orderId)
				.orElseThrow(() -> new NoSuchElementException("Order with ID=" + orderId + " was not found."));
	}
}


	
	


		
		
	
	
		




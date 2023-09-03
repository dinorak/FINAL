package online.marketplace.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import online.marketplace.controller.model.CustomerData;
import online.marketplace.controller.model.OrderData;
import online.marketplace.controller.model.ProductData;
import online.marketplace.entity.Customer;
import online.marketplace.entity.Order;
import online.marketplace.entity.Product;
import online.marketplace.repository.OrderDao;
import online.marketplace.repository.ProductDao;
import online.marketplace.repository.ProductMapper;
import online.marketplace.service.OnlineMarketplaceService;

@RestController
@Slf4j
public class OnlineMarketplaceController {
	
	private Product product;
	
	private OrderDao orderDao;
	
	private ProductMapper productMapper;
	
	private final OnlineMarketplaceService service;

    @Autowired
    public OnlineMarketplaceController(OnlineMarketplaceService service) {
        this.service = service;
    }
	
	@PostMapping("/customers")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insertCustomer(
			@RequestBody CustomerData customerData) {
				log.info("Inserting customer {}", customerData);
				return service.insertCustomer(customerData);
			}
	
	@GetMapping("/{customerId}")
	public Customer retrieveCustomer(@PathVariable Long customerId) {
			log.info("Retrieving customer with ID '{}'", customerId);
			return service.findCustomerById(customerId);
		}
	
	@PutMapping("/{customerId}/edit")
	public CustomerData editCustomer(@PathVariable Long customerId,
			@RequestBody CustomerData customerData) {
		log.info("Editing customer with ID '{}'", customerId);
		return service.editCustomer(customerId, customerData);
		
		
	}
	
	@DeleteMapping("/delete/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        log.info("Deleting customer with ID '{}'", customerId);
        service.deleteCustomer(customerId);
    }
	
	@PostMapping("/addproduct")
	public Product addProduct(@RequestBody ProductData product) {
		log.info("Adding product {}", product);
		return service.createProduct(product);
		
	}
	
	@PostMapping("/addorder")
	public OrderData addOrder(@RequestBody OrderData orderData) {
	    log.info("Adding order for customer with products {}", orderData);
	    return service.createOrder(orderData);
	}
	
	@GetMapping("/orders/{id}/products")
	public List<ProductDao> getProductsByOrderId(@PathVariable Long id) {
	    Optional<Order> optionalOrder = orderDao.findById(id);
	    if (optionalOrder.isPresent()) {
	        Order order = optionalOrder.get();
	        return order.getProducts().stream()
	                .map(productMapper::toDTO) // productMapper is an instance of a custom mapper interface that uses the @Mapper and @Mapping annotations
	                .collect(Collectors.toList());
	    }
	    return Collections.emptyList();
	}
}


		
		
	
	



package online.marketplace.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.marketplace.entity.Customer;
import online.marketplace.entity.Order;
import online.marketplace.entity.Product;

@Data
@NoArgsConstructor
public class OrderData {
    private Long orderId;
    private Customer customer;
    private Long customerId;
    private Set<ProductResponse> products = new HashSet<>();

    public OrderData(Order order) {
        orderId = order.getOrderId();
        customer = order.getCustomer();
        customerId = customer.getCustomerId();
        for (Product product : order.getProducts()) {
            products.add(new ProductResponse(product));
        }
    }

    @Data
    @NoArgsConstructor
    public static class ProductResponse {
        private Long productId;
        private String productName;

        public ProductResponse(Product product) {
            productId = product.getProductId();
            productName = product.getProduct();
        }
    }
}
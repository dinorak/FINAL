package online.marketplace.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import online.marketplace.entity.Product;

@Data
public class ProductData {
	
	private Long productId;
	private String product;
	
	 public ProductData() {
	 }
	
	public ProductData(Product product) {
        this.productId = product.getProductId();
        this.product = product.getProduct();
	
	
		
       
	
}
}



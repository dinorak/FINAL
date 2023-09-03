package online.marketplace.entity;

import java.util.List;

public class OrderRequest {
	
	private String customer;
    private List<String> productNames;
    
	public String getCustomer() {
		return customer;
		
	}
	public List<String> getProductNames() {
		return productNames;
		
	}

}

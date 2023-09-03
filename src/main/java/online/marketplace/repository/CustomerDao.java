package online.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.marketplace.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

	 Optional<Customer> findByCustomerFirstName(String firstName);

}

package online.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.marketplace.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	Optional<Product> findByProduct(String product);

}

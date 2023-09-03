package online.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.marketplace.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

	Optional<Order> findById(Long id);

}

package se.iths.erikthorell.webshopprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.erikthorell.webshopprojekt.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String userEmail);
}

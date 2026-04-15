package se.iths.erikthorell.webshopprojekt.repository;

import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Product findByCategory(Category category);

    Product findByPrice(@Positive BigDecimal price);

    Optional<Product> findById(Long id);

    List<Product> findByAdminOnlyFalse();
}

package se.iths.erikthorell.webshopprojekt.repository;

import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findByName(String name);

    public Product findByCategory(Category category);

    public Product findByPrice(@Positive double price);

    public Optional<Product> findById(Long id);
}

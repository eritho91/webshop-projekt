package se.iths.erikthorell.webshopprojekt.service;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts(Authentication auth) {

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return productRepository.findAll();
        } else {
            return productRepository.findByAdminOnlyFalse();
        }
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Product getProductByPrice(double price) {
        return productRepository.findByPrice(price);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product with id " + id + " not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product with id " + id + " not found"));

        productRepository.delete(product);

    }

    // Hämtar en specifik produkt baserat på ID
    public Product findById(Long id) {
        // Vi letar efter produkten. Om den inte finns kastar vi ett felmeddelande.
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkten hittades inte!"));
    }
}
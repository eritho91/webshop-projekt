package se.iths.erikthorell.webshopprojekt.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.repository.ProductRepository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(Authentication auth) {
        boolean isAdmin = auth != null &&
                auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return productRepository.findAll();
        } else {
            return productRepository.findByAdminOnlyFalse();
        }
    }

    public Map<Category, List<Product>> groupByCategory(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(p ->
                        p.getCategory().getId()
                ))
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
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

    public Product createProduct(Product product) {
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
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }
}
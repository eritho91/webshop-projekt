package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnAllProducts_whenAdmin() {
        Product product1 = new Product();
        Product product2 = new Product();

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        Authentication auth = new UsernamePasswordAuthenticationToken(
                "user",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        List<Product> result = productService.getProducts(auth);

        assertThat(result).hasSize(2);
        verify(productRepository).findAll();
        verify(productRepository, never()).findByAdminOnlyFalse();
    }

    @Test
    void shouldReturnNonAdminProducts_whenNotAdmin() {
        Product product = new Product();

        when(productRepository.findByAdminOnlyFalse())
                .thenReturn(List.of(product));

        Authentication auth = new UsernamePasswordAuthenticationToken(
                "user",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        List<Product> result = productService.getProducts(auth);

        assertThat(result).hasSize(1);
        verify(productRepository).findByAdminOnlyFalse();
        verify(productRepository, never()).findAll();
    }

    @Test
    void shouldGroupByCategory() {
        Category category1 = new Category();
        category1.setId(1L);

        Category category2 = new Category();
        category2.setId(2L);

        Product product1 = new Product();
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setCategory(category1);

        Product product3 = new Product();
        product3.setCategory(category2);

        // intentionally unsorted
        List<Product> products = List.of(product3, product1, product2);

        Map<Category, List<Product>> result =
                productService.groupByCategory(products);

        assertThat(result).hasSize(2);

        List<Category> keys = new ArrayList<>(result.keySet());
        assertThat(keys.get(0)).isEqualTo(category1);
        assertThat(keys.get(1)).isEqualTo(category2);

        assertThat(result.get(category1))
                .containsExactly(product1, product2);

        assertThat(result.get(category2))
                .containsExactly(product3);
    }

    @Test
    void shouldSaveProduct() {
        Product product = new Product();

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        verify(productRepository).save(product);
        assertThat(result).isEqualTo(product);
    }

    @Test
    void shouldDeleteProductWhenExists() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository).delete(product);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingAudiobook() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                productService.deleteProduct(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void shouldReturnProductWhenIdExists() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Product result = productService.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(productRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");
    }
}
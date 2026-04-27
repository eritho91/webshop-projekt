package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import se.iths.erikthorell.webshopprojekt.TestConfig;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Order;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.repository.CategoryRepository;
import se.iths.erikthorell.webshopprojekt.repository.ProductRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Import(TestConfig.class)
public class OrderDBTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void createOrder() {
        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        Product p = new Product();
        p.setName("Mat");
        p.setPrice(new BigDecimal("100"));
        p.setCategory(category);
        p = productRepository.save(p);

        Cart cart = new Cart();
        cart.addProduct(p);
        cart.addProduct(p);

        Order order = orderService.createOrder(cart, "jake@live.se");

        assertThat(order.getId()).isNotNull();
        assertThat(order.getTotalAmount()).isEqualByComparingTo("200");
        assertThat(order.getItems()).hasSize(1);
        assertThat(order.getItems().get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void getOrdersByUser() {
        Cart cart = new Cart();
        orderService.createOrder(cart, "jake@live.se");

        assertThat(orderService.getOrdersByUser("jake@live.se")).hasSize(1);
    }
}
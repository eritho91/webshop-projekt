package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Order;
import se.iths.erikthorell.webshopprojekt.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder() {
        //skapar produkt i kundvagn
        Cart cart = new Cart();
        String email = "jake@live.se";

        Order mockSavedOrder = new Order();
        mockSavedOrder.setUserEmail(email);

        when(orderRepository.save(any(Order.class))).thenReturn(mockSavedOrder);

        Order result = orderService.createOrder(cart, email);

        assertEquals(email, result.getUserEmail());
        verify(orderRepository).save(any());

    }

    @Test
    void getOrdersByUser() {
        String email = "jake@live.se";

        when(orderRepository.findByUserEmail(email)).thenReturn(List.of(new Order()));

        List<Order> results = orderService.getOrdersByUser(email);

        assertEquals(1, results.size());
        verify(orderRepository).findByUserEmail(email);

    }
}
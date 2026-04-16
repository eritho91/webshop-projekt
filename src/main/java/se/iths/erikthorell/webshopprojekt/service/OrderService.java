package se.iths.erikthorell.webshopprojekt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.CartItem;
import se.iths.erikthorell.webshopprojekt.model.Order;
import se.iths.erikthorell.webshopprojekt.model.OrderItem;
import se.iths.erikthorell.webshopprojekt.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional
    public Order createOrder(Cart cart, String userEmail) {

        // skapar ordern
        Order order = new Order();

        // kopplar ordern till email
        order.setUserEmail(userEmail);

        // spara den totala summan från kundvagn
        order.setTotalAmount(cart.getTotalCartPrice());

        // skapar orderrader
        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());

            // sparar nuvarande pris på orderrad
            orderItem.setPriceAtPurchase(item.getProduct().getPrice());

            // lägger till skapade raden i order objektet
            order.getItems().add(orderItem);
        }


        return orderRepository.save(order);
    }

    // hämtar en lista över alla ordrar från en specifik kund
    public List<Order> getOrdersByUser(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }
}
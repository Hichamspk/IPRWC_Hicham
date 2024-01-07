package com.iprwc2.dao;

import com.iprwc2.model.ShopOrder;
import com.iprwc2.repository.OrderRepositroy;
import com.iprwc2.repository.UserRepository;
import com.iprwc2.dao.OrderItemDao;
import javassist.NotFoundException;
import com.iprwc2.model.User;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDao {

    private final OrderRepositroy orderRepository;
    private final UserRepository userRepository;

    public OrderDao(OrderRepositroy orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<ShopOrder> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Optional<ShopOrder> getOrderById(Long orderId){
        return this.orderRepository.findById(orderId);
    }

    public List<ShopOrder> getAllUserOrders(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return Collections.emptyList();
        }
        User user1 = user.get();
        return this.orderRepository.findShopOrdersByUser(user1);
    }

    public ShopOrder saveNewOrder(ShopOrder shopOrder) {
        Optional<User> user = userRepository.findByEmail(shopOrder.getUser().getEmail());
        if (user.isPresent()) {
            shopOrder.setUser(user.get());
        } else {
            throw new IllegalStateException("Couldnt save User");

        }
        return this.orderRepository.save(shopOrder);
    }

    public ShopOrder updateOrderStatus(ShopOrder newOrder, Long orderId) throws NotFoundException {
        Optional<ShopOrder> oldOrder = this.orderRepository.findById(orderId);
        if (oldOrder.isEmpty()){
            throw new NotFoundException("User with id: " + orderId + " not found");
        }
        ShopOrder order = oldOrder.get();
        order.setOrderStatus(newOrder.getOrderStatus());
        return this.orderRepository.save(order);
    }

}

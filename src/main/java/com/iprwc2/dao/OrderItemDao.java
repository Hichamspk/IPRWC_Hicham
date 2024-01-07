package com.iprwc2.dao;

import com.iprwc2.model.OrderItem;
import com.iprwc2.model.ShopOrder;
import com.iprwc2.repository.OrderItemRepository;
import com.iprwc2.repository.OrderRepositroy;
import com.iprwc2.repository.ProductRepository;
import org.springframework.stereotype.Component;
import javassist.NotFoundException;


import java.util.List;
import java.util.Optional;

@Component
public class OrderItemDao{
    private final OrderItemRepository orderItemRepository;
    private final OrderRepositroy orderRepository;
    private final ProductRepository productRepository;

    public OrderItemDao(OrderItemRepository orderItemRepository, OrderRepositroy orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return this.orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long orderItemId) {
        return this.orderItemRepository.findById(orderItemId);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        Optional<ShopOrder> shopOrder = this.orderRepository.findById(orderId);
        if (shopOrder.isEmpty()) {
            return List.of();
        }
        return this.orderItemRepository.getOrderItemsByShopOrder(shopOrder.get());
    }

    public OrderItem saveNewOrderItem(OrderItem orderItem) throws NotFoundException {
        Optional<ShopOrder> shopOrder = this.orderRepository.findById(orderItem.getShopOrder().getId());
        if (shopOrder.isEmpty()) {
            throw new NotFoundException("ShopOrder not found");
        }


        orderItem.setShopOrder(shopOrder.get());

        // Ensure that the product with the given productId exists before saving
        if (!productRepository.existsById((long)orderItem.getProductId())) {
            throw new NotFoundException("Product not found");
        }

        return this.orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(OrderItem newOrderItem, Long orderItemId) throws NotFoundException {
        Optional<OrderItem> oldOrderItem = this.orderItemRepository.findById(orderItemId);
        if (oldOrderItem.isEmpty()) {
            throw new NotFoundException("OrderItem with id: " + orderItemId + " not found");
        }

        OrderItem orderItem = oldOrderItem.get();
        orderItem.setProductId(newOrderItem.getProductId());
        orderItem.setQuantity(newOrderItem.getQuantity());
        orderItem.setSubTotal(newOrderItem.getSubTotal());

        return this.orderItemRepository.save(orderItem);
    }
}

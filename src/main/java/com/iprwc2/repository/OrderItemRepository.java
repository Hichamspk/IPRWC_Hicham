package com.iprwc2.repository;


import com.iprwc2.model.ShopOrder;
import com.iprwc2.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> getOrderItemsByShopOrder(ShopOrder shopOrder);
}

package com.iprwc2.repository;

import com.iprwc2.model.ShopOrder;
import com.iprwc2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositroy extends JpaRepository<ShopOrder, Long> {
    List<ShopOrder> findShopOrdersByUser(User user);
}

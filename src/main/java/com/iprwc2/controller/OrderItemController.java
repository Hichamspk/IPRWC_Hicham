package com.iprwc2.controller;


import com.iprwc2.dao.OrderItemDao;
import com.iprwc2.model.ApiResponse;
import com.iprwc2.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/order-items")
//@CrossOrigin(origins = "*")
public class OrderItemController {

    @Autowired
    private OrderItemDao orderItemDao;

    public OrderItemController(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }


    @GetMapping("/all")
    @ResponseBody
    public ApiResponse<List<OrderItem>> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
            return new ApiResponse<>(HttpStatus.ACCEPTED, orderItems);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Could not fetch all order items");
        }
    }

    // Get one order item by id
    @GetMapping("/{orderItemId}")
    @ResponseBody
    public ApiResponse<Optional<OrderItem>> getOrderItemById(@PathVariable Long orderItemId) {
        try {
            Optional<OrderItem> orderItem = orderItemDao.getOrderItemById(orderItemId);
            return new ApiResponse<>(HttpStatus.ACCEPTED, orderItem);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Order item not found");
        }
    }


    @GetMapping("/by-order/{orderId}")
    @ResponseBody
    public ApiResponse<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            List<OrderItem> orderItems = orderItemDao.getOrderItemsByOrderId(orderId);
            return new ApiResponse<>(HttpStatus.ACCEPTED, orderItems);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Order items not found for the given order ID");
        }
    }

    @PostMapping("/new-order-item")
    @ResponseBody
    public ApiResponse<OrderItem> placeNewOrderItem(@RequestBody OrderItem orderItemRequest) {
        try {
            OrderItem orderItem = orderItemDao.saveNewOrderItem(orderItemRequest);
            return new ApiResponse<>(HttpStatus.ACCEPTED, orderItem);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Couldn't place order item.");
        }
    }

    // Update an order item
    @PutMapping("/{orderItemId}")
    @ResponseBody
    public ApiResponse<OrderItem> updateOrderItem(
            @RequestBody OrderItem orderItemRequest,
            @PathVariable Long orderItemId) {
        try {
            OrderItem orderItem = orderItemDao.updateOrderItem(orderItemRequest, orderItemId);
            return new ApiResponse<>(HttpStatus.ACCEPTED, orderItem);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Couldn't update order item.");
        }
    }
}

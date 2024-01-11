package com.iprwc2.controller;

import com.iprwc2.dao.OrderDao;
import com.iprwc2.DTO.ApiResponse;
import com.iprwc2.model.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1/orders")
//@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //Get all orders
    @RequestMapping(method = RequestMethod.GET, value = "/all-orders")
    @ResponseBody
    ApiResponse<List<ShopOrder>> getAllOrders(){
        try {
            List<ShopOrder> shopOrders = this.orderDao.getAllOrders();
            return new ApiResponse<>(HttpStatus.ACCEPTED, shopOrders);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Could not fetch all orders");
        }
    }

    //Get one order by id
    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    @ResponseBody
    ApiResponse<Optional<ShopOrder>> getOrderBy(@PathVariable Long orderId) {
        try {
            Optional<ShopOrder> order = this.orderDao.getOrderById(orderId);
            return new ApiResponse<>(HttpStatus.ACCEPTED, order);
        } catch (Exception e){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

    //Place a new order
    @RequestMapping(method = RequestMethod.POST, value = "/new-order")
    @ResponseBody
    public ApiResponse<ShopOrder> placeNewOrder(
            @RequestBody ShopOrder shopOrderRequest
    ){
        try {
            ShopOrder order = this.orderDao.saveNewOrder(shopOrderRequest);
            return new ApiResponse<>(HttpStatus.ACCEPTED, order);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Couldn't place order.");
        }
    }

    //Update an order
    @RequestMapping(method = RequestMethod.PUT, value = "/{orderId}")
    @ResponseBody
    public ApiResponse<ShopOrder> updateOrderStatus(
            @RequestBody ShopOrder shopOrderRequest,
            @PathVariable Long orderId
    ){
        try {
            ShopOrder order = this.orderDao.updateOrderStatus(shopOrderRequest, orderId);
            return new ApiResponse<>(HttpStatus.ACCEPTED, order);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Couldn't place order.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{email}")
    @ResponseBody
    ApiResponse<List<ShopOrder>> getAllUserOrders(@PathVariable String email) {
        try {
            List<ShopOrder> orders = this.orderDao.getAllUserOrders(email);
            return new ApiResponse<>(HttpStatus.ACCEPTED, orders);
        } catch (Exception e){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Orders not found");
        }
    }
}

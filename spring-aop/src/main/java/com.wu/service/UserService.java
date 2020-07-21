package com.wu.service;

import com.wu.model.Order;
import com.wu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dihua.wuResult
 * @description
 * @create 2020/7/21
 */
@Service
public class UserService {

    @Autowired
    private OrderService orderService;

    public User getUser(User user){
        Order order=orderService.getOrder(1L);
        List<Order> orders =new ArrayList<Order>();
        orders.add(order);
        user.setOrders(orders);
        return user;
    }
}

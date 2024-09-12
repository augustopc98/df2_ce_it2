package com.example.demo.services;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createOrder(Long id, String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, Long orderItemId);
    BigDecimal calculateTotal(Long orderId);
    void sendForDelivery(Long orderId);
    void updateDeliveryStatus(Long orderId, String status);
}

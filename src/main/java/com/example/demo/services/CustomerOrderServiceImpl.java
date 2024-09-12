package com.example.demo.services;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createOrder(Long id, String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(id, customerEmail, customerAddress, orderDate, items);
        return customerOrderRepository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.getItems().add(item);
        customerOrderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, Long itemId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.getItems().removeIf(item -> item.getId().equals(itemId));
        customerOrderRepository.save(order);
    }

    @Override
    public BigDecimal calculateTotal(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return order.calculateTotal();
    }

    @Override
    public void sendForDelivery(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.sendForDelivery();
        customerOrderRepository.save(order);
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateDeliveryStatus(status);
        customerOrderRepository.save(order);
    }
}

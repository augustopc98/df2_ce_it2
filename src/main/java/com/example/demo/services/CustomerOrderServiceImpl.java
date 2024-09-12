package com.example.demo.services;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
        Optional<CustomerOrder> optionalOrder = customerOrderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            CustomerOrder order = optionalOrder.get();
            order.addOrderItem(item);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void removeOrderItem(Long orderId, Long orderItemId) {
        Optional<CustomerOrder> optionalOrder = customerOrderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            CustomerOrder order = optionalOrder.get();
            order.removeOrderItem(new OrderItem(orderItemId, null, 0, null));
            customerOrderRepository.save(order);
        }
    }

    @Override
    public BigDecimal calculateTotal(Long orderId) {
        Optional<CustomerOrder> optionalOrder = customerOrderRepository.findById(orderId);
        return optionalOrder.map(CustomerOrder::calculateTotal).orElse(BigDecimal.ZERO);
    }

    @Override
    public void sendForDelivery(Long orderId) {
        Optional<CustomerOrder> optionalOrder = customerOrderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            CustomerOrder order = optionalOrder.get();
            order.sendForDelivery();
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        Optional<CustomerOrder> optionalOrder = customerOrderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            CustomerOrder order = optionalOrder.get();
            order.updateDeliveryStatus(status);
            customerOrderRepository.save(order);
        }
    }
}

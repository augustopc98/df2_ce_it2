package com.example.demo.controllers;

import com.example.demo.models.CustomerOrder;
import com.example.demo.models.OrderItem;
import com.example.demo.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return customerOrderService.createOrder(order.getId(), order.getCustomerEmail(), order.getCustomerAddress(), new Date(), order.getItems());
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public void removeOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        customerOrderService.removeOrderItem(orderId, itemId);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal calculateTotal(@PathVariable Long orderId) {
        return customerOrderService.calculateTotal(orderId);
    }

    @PostMapping("/{orderId}/sendForDelivery")
    public void sendForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendForDelivery(orderId);
    }

    @PostMapping("/{orderId}/updateDeliveryStatus")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateDeliveryStatus(orderId, status);
    }
}

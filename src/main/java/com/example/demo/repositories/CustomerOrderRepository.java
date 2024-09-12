package com.example.demo.repositories;

import com.example.demo.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Optional<CustomerOrder> findById(Long id);
    void delete(CustomerOrder customerOrder);
}

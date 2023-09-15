package com.example.kn.repository;

import com.example.kn.model.Order;
import com.example.kn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long id);


    List<Order> findAllByOrderDate(LocalDate date);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol.product = :product")
    List<Order> findOrdersByProduct(@Param("product") Product product);
}

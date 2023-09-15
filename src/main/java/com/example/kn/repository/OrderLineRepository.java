package com.example.kn.repository;


import com.example.kn.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    Optional<OrderLine> findById(Long id);

}

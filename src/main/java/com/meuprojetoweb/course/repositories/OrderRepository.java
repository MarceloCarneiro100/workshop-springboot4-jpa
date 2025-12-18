package com.meuprojetoweb.course.repositories;

import com.meuprojetoweb.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

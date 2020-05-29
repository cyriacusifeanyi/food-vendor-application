package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
}

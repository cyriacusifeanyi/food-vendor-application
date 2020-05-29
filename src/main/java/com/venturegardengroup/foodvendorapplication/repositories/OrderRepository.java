package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    void deleteOrderByOrderStatusId(OrderStatus orderStatusId);

    List<Order> findByVendorIdAndDateCreated(Vendor vendorId, LocalDate dateCreated);
    List<Order> findAllByOrderStatusId(OrderStatus orderStatusId);
    List<Order> findByVendorId(Vendor vendorId);

}

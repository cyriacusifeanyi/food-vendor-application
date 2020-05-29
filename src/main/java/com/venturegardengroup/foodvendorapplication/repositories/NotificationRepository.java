package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.NotificationStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    void deleteNotificationByNotificationStatusId(NotificationStatus notificationStatus);
    void deleteNotificationByCustomerId(Customer customerId);
    void deleteNotificationByOrderId(Order orderId);
    void deleteNotificationByVendorId(Vendor vendorId);

    List<Notification> findByCustomerId(Customer customerId);
    List<Notification> findAllByNotificationStatusId(NotificationStatus notificationStatusId);
}

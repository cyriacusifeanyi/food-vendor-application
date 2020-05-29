package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<NotificationStatus,Integer> {

}

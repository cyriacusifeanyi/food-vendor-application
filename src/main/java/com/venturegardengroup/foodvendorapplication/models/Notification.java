package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notifications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Notification extends AbstractPersistable<Long> {
    @ManyToOne
    private Vendor vendorId;
    @ManyToOne
    private Customer customerId;
    @ManyToOne
    private Order orderId;
    @ManyToOne
    private NotificationStatus notificationStatusId;

    @Column(name = "message")
    private String message;
    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated;

}

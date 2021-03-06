package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer extends AbstractPersistable<Long> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "account_balance")
    private BigDecimal accountBalance = new BigDecimal(0);

    @OneToMany(mappedBy = "customerId")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "customerId")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated;

}
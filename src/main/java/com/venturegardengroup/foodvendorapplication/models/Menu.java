package com.venturegardengroup.foodvendorapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "menus")
public class Menu extends AbstractPersistable<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_recurring")
    private boolean isRecurring;
    @Column(name = "frequency_of_recurrence")
    private int frequencyOfRecurrence;

    @ManyToOne
    private Vendor vendorId;
    @ManyToMany(mappedBy = "menus")
    private List<Order> orders = new ArrayList<>();

    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated;

}
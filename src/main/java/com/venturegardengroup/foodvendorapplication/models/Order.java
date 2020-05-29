package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Order extends AbstractPersistable<Long> {
    @ManyToOne
    private Customer customerId;
    @ManyToOne
    private Vendor vendorId;
    @ManyToOne
    private OrderStatus orderStatusId;

    @ManyToMany
    @JoinTable(
            name = "item_ordered",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<Menu> menus = new ArrayList<>();

    //    @Column(name = "description")
//    private String description;
    @Column(name = "amount_due")
    private BigDecimal amountDue = new BigDecimal(0);
    @Column(name = "amount_paid")
    private BigDecimal amountPaid = new BigDecimal(0);
    @Column(name = "amount_outstanding")
    private BigDecimal amountOutstanding = new BigDecimal(0);
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "time_created")
    private LocalTime timeCreated;


    public BigDecimal getAmountDue() {
        amountDue = new BigDecimal(0);
        for (Menu menu : menus) {
            amountDue = amountDue.add(menu.getPrice());
        }
        return amountDue;
    }
}

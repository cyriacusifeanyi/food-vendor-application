package com.venturegardengroup.foodvendorapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_statuses")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  OrderStatus extends AbstractPersistable<Integer> {
    //how do i initialize this table with values like "pending",
    // "in-progress", "completed", "canceled",

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "orderStatusId")
    private List<Order> orders = new ArrayList<>();

}

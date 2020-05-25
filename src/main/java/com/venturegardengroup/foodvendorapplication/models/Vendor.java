package com.venturegardengroup.foodvendorapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "vendors")
public class Vendor extends AbstractPersistable<Long> {

    @Column(name = "business_name")
    private String businessName;
    @Column(name = "phone_number")
    private String phoneNumber;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "auth_id", referencedColumnName = "id")
//    private Auth auth;
//    @OneToMany(mappedBy = "vendorId")
//    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "vendorId")
    private List<Menu> menus = new ArrayList<>();
    @OneToMany(mappedBy = "vendorId")
    private List<Notification> notifications = new ArrayList<>();

    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated;

}
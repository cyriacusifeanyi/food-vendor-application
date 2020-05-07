package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity(name = "auth")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Date date_time_created;

    @JsonIgnore
    @OneToOne(mappedBy = "auth")
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Vendor vendor;

    @JsonIgnore
    @OneToOne(mappedBy = "auth")
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Customer customer;


    public Auth(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(Date date_time_created) {
        this.date_time_created = date_time_created;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

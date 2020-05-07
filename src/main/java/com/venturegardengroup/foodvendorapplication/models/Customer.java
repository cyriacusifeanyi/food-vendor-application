package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Date date_time_created;
    private Double amount_outstanding;

    @OneToMany(mappedBy ="customer")
    @JsonIgnore
    private List<Notification> notifications;

    @OneToMany(mappedBy ="customer")
    @JsonIgnore
    private Set<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "email", referencedColumnName = "email")
    private Auth auth;

    public Customer(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(Date date_time_created) {
        this.date_time_created = date_time_created;
    }

    public Double getAmount_outstanding() {
        return amount_outstanding;
    }

    public void setAmount_outstanding(Double amount_outstanding) {
        this.amount_outstanding = amount_outstanding;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
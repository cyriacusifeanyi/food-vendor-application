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
import java.util.Date;
import java.util.Set;

@Entity(name = "vendors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Vendor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String business_name;
    private String email;
    private String phone_number;
    private Date date_time_created;

    @JsonIgnore
    @OneToMany(mappedBy ="vendor")
    private Set<Menu> menus;

    @JsonIgnore
    @OneToMany(mappedBy ="vendor")
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy ="vendor")
    private Set<Notification> notifications;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "email", referencedColumnName = "email")
    private Auth auth;

    public Vendor(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
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

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false, updatable = false)
    private Long customer_id;
    @Column(insertable = false, updatable = false)
    private Long vendor_id;
    private String description;
//    private Set<Menu> items_ordered;
    private Double amount_due;
    private Double amount_paid;
    private Double amount_outstanding;
    @Column(insertable = false, updatable = false)
    private Long order_status_id;
    @Column(insertable = false, updatable = false)
    private Long menu_id;
    private Date date_time_of_order;

    @OneToMany(mappedBy ="order")
    @JsonIgnore
    private Set<Notification> notifications;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", nullable = false, referencedColumnName = "id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vendor_id", nullable = false, referencedColumnName = "id")
    private Vendor vendor;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_status_id", nullable = false, referencedColumnName = "id")
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="menu_id", nullable = false, referencedColumnName = "id")
    private Menu menu;

    public Order(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Long vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(Double amount_due) {
        this.amount_due = amount_due;
    }

    public Double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(Double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public Double getAmount_outstanding() {
        return amount_outstanding;
    }

    public void setAmount_outstanding(Double amount_outstanding) {
        this.amount_outstanding = amount_outstanding;
    }

    public Long getOrder_status_id() {
        return order_status_id;
    }

    public void setOrder_status_id(Long order_status_id) {
        this.order_status_id = order_status_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public Date getDate_time_of_order() {
        return date_time_of_order;
    }

    public void setDate_time_of_order(Date date_time_of_order) {
        this.date_time_of_order = date_time_of_order;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "notifications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false, updatable = false)
    private Long vendor_id;
    @Column(insertable = false, updatable = false)
    private Long customer_id;
    @Column(insertable = false, updatable = false)
    private Long order_id;
    private String message;
    private Date date_time_created;
    @Column(insertable = false, updatable = false)
    private Long message_status_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="vendor_id", nullable = false, referencedColumnName = "id")
    private Vendor vendor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false, referencedColumnName = "id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false, referencedColumnName = "id")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="message_status_id", nullable = false, referencedColumnName = "id")
    private MessageStatus messageStatus;

    public Notification(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Long vendor_id) {
        this.vendor_id = vendor_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(Date date_time_created) {
        this.date_time_created = date_time_created;
    }

    public Long getMessage_status_id() {
        return message_status_id;
    }

    public void setMessage_status_id(Long message_status_id) {
        this.message_status_id = message_status_id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }
}

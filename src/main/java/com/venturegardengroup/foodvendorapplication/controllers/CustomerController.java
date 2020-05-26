package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private MessageStatusRepository messageStatusRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private VendorRepository vendorRepository;


    @GetMapping()
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("{id}")
    public Customer getOne(@PathVariable Long id) {
        return customerRepository.getOne(id);
    }

    @PostMapping()
    public Customer create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phoneNumber,
            @RequestParam BigDecimal accountBalance) {

        Customer newCustomer = new Customer(
                firstName, lastName, phoneNumber,
                accountBalance,
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now());

        return customerRepository.save(newCustomer);
    }

    //    used by only customer
    @Transactional
    @PostMapping("{id}/orders")
    public Order createOrder(@PathVariable Long id,
                         @RequestParam Long menuId,
                         @RequestParam int orderStatusId,
                         @RequestParam BigDecimal amountDue,
                         @RequestParam BigDecimal amountPaid,
                         @RequestParam BigDecimal amountOutstanding){

        Customer customer = customerRepository.getOne(id);
        Vendor vendor = vendorRepository.getOne(id);
        Menu menu = menuRepository.getOne(menuId);

        OrderStatus orderStatus = orderStatusRepository.getOne(orderStatusId);

        Order newOrder = new Order(
                customer,
                vendor,
                orderStatus,
                new ArrayList<>(),
                amountDue,
                amountPaid,
                amountOutstanding,
                LocalDate.now(),
                LocalTime.now()
        );
        newOrder.getMenus().add(menu);

        return orderRepository.save(newOrder);
    }

    //this controller can view associated notifications
//    unstable
    @GetMapping("{id}/notifications")
    public List<Notification> getCustomerNotification(@PathVariable Long id) {

        return notificationRepository.findByCustomerId(getOne(id));
    }

    @PostMapping("{customerId}/notifications/{notificationId}")
    public Notification updateNotificationStatus(@PathVariable Long customerId,
                                           @PathVariable Long notificationId,
                                           @RequestBody int messageStatusId){
        Notification existingNotification = notificationRepository.getOne(notificationId);
        MessageStatus newMessageStatus = messageStatusRepository.getOne(messageStatusId);
        existingNotification.setMessageStatusId(newMessageStatus);
        return notificationRepository.saveAndFlush(existingNotification);
    }


}
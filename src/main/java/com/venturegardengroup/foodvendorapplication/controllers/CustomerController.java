package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/customers")
@Controller
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

//this controller creates customers, orders
//this controller can view associated notifications
//this controller can change notification status to READ

    @GetMapping("/customers")
    public String list(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer/customers";
    }

    @PostMapping("/customer")
    public String create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phoneNumber,
//            @RequestParam Auth auth,
            @RequestParam BigDecimal accountBalance) {

        customerRepository.save(new Customer(
                firstName, lastName, phoneNumber,
                accountBalance,
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now()));
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("customer", customerRepository.getOne(id));
        return "customer/customer";
    }

//    new order moved to orderController


    //this controller can view associated notifications
//    unstable
    @GetMapping("/notifications/customer/{id}")
    public String getCustomerNotification(Model model, @PathVariable Long id) {
//        query/find by customer id -> notificationRepository
        model.addAttribute("auth", notificationRepository.findAll());
        return "notification/notifications";
    }

    //this controller can change notification status to READ
//    method is PUT / PATCH
    @PostMapping("/customers/{customerId}/notifications/{notificationId}")
    public String updateNotificationStatus(@PathVariable Long customerId,
                                           @PathVariable Long notificationId,
                                           @RequestParam int messageStatusId){
        Notification currentNotification = notificationRepository.getOne(notificationId);
        MessageStatus newMessageStatus = messageStatusRepository.getOne(messageStatusId);
        currentNotification.setMessageStatusId(newMessageStatus);
        notificationRepository.save(currentNotification);
        return "redirect:/customers/" + customerId;
    }


}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
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

//@RestController
//@RequestMapping("/notifications")
@Controller
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageStatusRepository messageStatusRepository;


    //    view all
    @GetMapping("/notifications")
    public String list(Model model) {
        model.addAttribute("notifications", notificationRepository.findAll());
        model.addAttribute("vendors", vendorRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("messageStatuses", messageStatusRepository.findAll());

        return "notification/notifications";
    }
    //    view one
    @GetMapping("/notifications/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("notification", notificationRepository.getOne(id));
        return "notification/notification";
    }

    //    create
    //vendorId customerId orderId messageStatusId
    @PostMapping("/notification")
    public String create(@RequestParam Long vendorId,
                         @RequestParam Long customerId,
                         @RequestParam Long orderId,
                         @RequestParam int messageStatusId,
                         @RequestParam String message){
//        int defaultMessageStatusId = 1;
        Customer customer = customerRepository.getOne(customerId);
        Vendor vendor = vendorRepository.getOne(vendorId);
        Order order = orderRepository.getOne(orderId);
        MessageStatus messageStatus = messageStatusRepository.getOne(messageStatusId);

        Notification newNotification = new Notification(
                vendor,
                customer,
                order,
                messageStatus,
                message,
                LocalDateTime.now()
        );

        notificationRepository.save(newNotification);
        return "redirect:/notifications";
    }
}
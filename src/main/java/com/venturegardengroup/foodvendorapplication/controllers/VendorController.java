package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping("/vendors")
@Controller
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MessageStatusRepository messageStatusRepository;

    @GetMapping("/vendors")
    public String list(Model model) {
        model.addAttribute("vendors", vendorRepository.findAll());
        return "vendor/vendors";
    }

    @PostMapping("/vendor")
    public String create(@RequestParam String businessName,
                         @RequestParam String phoneNumber){
        vendorRepository.save(new Vendor(
                businessName, phoneNumber,
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now()));
        return "redirect:/vendors";
    }

    @GetMapping("/vendors/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("vendor", vendorRepository.getOne(id));
        return "vendor/vendor";
    }




    //    generate daily sales report
    @PostMapping("/vendors/{id}/daily-sales-report")
    public String generateDailySalesReport(Model model,
                                           @PathVariable Long id){
//        get all orders
        List<Order> currentOrder = orderRepository.findAll();
//        with vendorId = {id}

//        same date as today

        model.addAttribute("salesReport", notificationRepository.findAll());
        return "";
    }

    //    send notification/create notification
    @Secured("ADMIN")
    @PostMapping("/vendor/{id}/notification")
    public String notify(@PathVariable Long id,
                            @RequestParam Long customerId,
                            @RequestParam Long orderId,
                            @RequestParam int messageStatusId,
                            @RequestParam String message){

        Vendor vendor = vendorRepository.getOne(id);
        Customer customer = customerRepository.getOne(customerId);
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
        return "redirect:/vendors/" + id;
    }

}
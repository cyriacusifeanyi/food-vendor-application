package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MessageStatusRepository messageStatusRepository;

    @GetMapping()
    public List<Vendor> list() {
        return vendorRepository.findAll();
    }

    @GetMapping("{id}")
    public Vendor getOne(@PathVariable Long id) {
        return vendorRepository.getOne(id);
    }

    @PostMapping()
    public Vendor create(@RequestParam String businessName,
                         @RequestParam String phoneNumber){

        Vendor newVendor = new Vendor(
                businessName, phoneNumber,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now());
        return vendorRepository.save(newVendor);
    }

    //    generate daily sales report
    @PostMapping("{id}/daily-sales-report")
    public List<List<String>> generateDailySalesReport(@PathVariable Long id){
        List<Order> todayOrder = orderRepository.findByVendorIdAndDateCreated(
                getOne(id),
                LocalDate.now());

        List<List<String>> salesReport =new ArrayList<>();
        int j = 0;
        for (Order order:todayOrder) {
            List<String> list1 =new ArrayList<>();
            int i =0;

            for (Menu menu: order.getMenus()){
                list1.add(i, menu.getName());
                list1.add(i, String.valueOf(menu.getPrice()));
                list1.add(i, menu.getDescription());
                i++;
            }

            salesReport.add(j, list1);
            salesReport.add(j, Collections.singletonList(order.getAmountDue().toString()));
            salesReport.add(j, Collections.singletonList(order.getAmountOutstanding().toString()));
            salesReport.add(j, Collections.singletonList(order.getVendorId().getBusinessName()));
            j++;
        }

        return salesReport;
    }

    //    send notification/create notification
    @PostMapping("{id}/notifications")
    public Notification sendNotification(@PathVariable Long id,
                                         @RequestParam Long customerId,
                                         @RequestParam Long orderId,
                                         @RequestParam int messageStatusId,
                                         @RequestParam String message){

        Vendor vendor = getOne(id);
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
        return notificationRepository.save(newNotification);
    }

    @PostMapping("{id}/menus")
    public Menu createMenu(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam BigDecimal price,
                           @RequestParam int quantity,
                           @RequestParam boolean isRecurring,
                           @RequestParam int frequencyOfRecurrence){
        Vendor vendor = getOne(id);

        Menu newMenu = new Menu(
                name, description, price, quantity,
                isRecurring, frequencyOfRecurrence,
                vendor, new ArrayList<>(), LocalDateTime.now()
        );
        return menuRepository.save(newMenu);
    }


}
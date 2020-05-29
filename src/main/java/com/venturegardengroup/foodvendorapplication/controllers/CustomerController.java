package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.services.CustomerService;
import com.venturegardengroup.foodvendorapplication.services.NotificationService;
import com.venturegardengroup.foodvendorapplication.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Customer> list() {
        return customerService.list();
    }

    @GetMapping("{id}")
    public Customer getOne(@PathVariable Long id) {
        return customerService.getOne(id);
    }

    @PostMapping()
    public Customer create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phoneNumber,
            @RequestParam BigDecimal accountBalance) {
        return customerService.create(firstName, lastName, phoneNumber, accountBalance);
    }

    //    used by only customer
    @PostMapping("{id}/orders")
    public Order createOrder(@PathVariable Long id,
                             @RequestParam Long menuId,
                             @RequestParam int orderStatusId,
                             @RequestParam BigDecimal amountPaid){

        Customer customer = this.getOne(id);
        return this.orderService.create(customer, menuId,
                orderStatusId,amountPaid);
    }

    //    PATCH order
    @RequestMapping(value = "{id}/orders/{orderId}", method = RequestMethod.PATCH)
    public @ResponseBody Order updateOrder(@PathVariable Long id,
                                           @PathVariable Long orderId,
                                           @RequestBody Order order) {
        Customer customer = this.getOne(id);
        return this.orderService.update(customer, orderId, order);
    }

    //    DELETE order
    @RequestMapping(value = "{id}/orders/{orderId}", method = RequestMethod.DELETE)
    public @ResponseBody void cancelOrder(@PathVariable Long id,
                                          @PathVariable Long orderId) {
        Customer customer = this.getOne(id);
        this.orderService.cancel(customer, orderId);
    }

    /*add a menu to order*/
    @PostMapping("orders/{id}/menus/{menuId}")
    public @ResponseBody Order addMenuToOrder(@PathVariable Long id,
                                              @PathVariable Long menuId){
        return orderService.addMenuToOrder(id, menuId);
    }

    //this controller can view associated notifications
    @GetMapping("{id}/notifications")
    public List<Notification> getCustomerNotification(@PathVariable Long id) {
        return this.notificationService.findByCustomerId(getOne(id));
    }

    //    use it too for updating NotificationStatus
    @RequestMapping(value = "{id}/notification/{notificationId}/notification-status/{notificationStatusId}",
            method = RequestMethod.PATCH)
    public @ResponseBody Notification updateNotificationNotificationStatusId(
            @PathVariable Long id,
            @PathVariable Long notificationId,
            @PathVariable int notificationStatusId){
        Customer customer = this.getOne(id);
        return this.notificationService.updateNotificationStatusId(customer, notificationId, notificationStatusId);
    }

}
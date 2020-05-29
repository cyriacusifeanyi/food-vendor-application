package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.services.MenuService;
import com.venturegardengroup.foodvendorapplication.services.NotificationService;
import com.venturegardengroup.foodvendorapplication.services.OrderService;
import com.venturegardengroup.foodvendorapplication.services.SalesService;
import com.venturegardengroup.foodvendorapplication.services.VendorService;
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
@RequestMapping("/vendors")
//@BasePathAwareController
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private MenuService menuService;

    @GetMapping()
    public List<Vendor> list() {
        return this.vendorService.list();
    }

    @GetMapping("{id}")
    public Vendor getOne(@PathVariable Long id) {
        return this.vendorService.getOne(id);
    }

    @PostMapping()
    public Vendor create(@RequestParam String businessName,
                         @RequestParam String phoneNumber){
        return this.vendorService.create(businessName, phoneNumber);
    }

    /*generate daily sales report using the order resources*/
    @GetMapping("{id}/daily-sales-report")
    public @ResponseBody List<List<String>> generateDailySalesReport(@PathVariable Long id){
        return this.salesService.generateVendorDailySalesReport(id);
    }

    /*sending notification using notification related resources*/
    @PostMapping("{id}/notifications")
    public @ResponseBody Notification sendNotification(@PathVariable Long id,
                                                       @RequestParam Long orderId,
                                                       @RequestParam int notificationStatusId,
                                                       @RequestParam String message){
        Vendor vendor = this.getOne(id);
        return this.notificationService.createNotification(vendor,
                orderId, notificationStatusId, message);
    }
    /*PATCH notification*/
    @RequestMapping(value = "{id}/notifications/{notificationId}", method = RequestMethod.PATCH)
    public @ResponseBody Notification updateNotification(@PathVariable Long id,
                                                         @PathVariable Long notificationId,
                                                         @RequestParam String message){
        Vendor vendor = this.getOne(id);
        return this.notificationService.updateNotification(
                vendor, notificationId, message);
    }

    /*Handling Menu related resources */
    @PostMapping("{id}/menus")
    public @ResponseBody Menu createMenu(@PathVariable Long id,
                                         @RequestParam String name,
                                         @RequestParam String description,
                                         @RequestParam BigDecimal price,
                                         @RequestParam int quantity,
                                         @RequestParam boolean isRecurring,
                                         @RequestParam int frequencyOfRecurrence){
        Vendor vendor = getOne(id);
        return this.menuService.create(vendor, name, description, price,
                quantity, isRecurring, frequencyOfRecurrence);
    }

    @RequestMapping(value = "{id}/menus/{menuId}", method = RequestMethod.PATCH)
    public @ResponseBody Menu updateMenu(@PathVariable Long id,
                                         @PathVariable Long menuId,
                                         @RequestParam String name,
                                         @RequestParam String description,
                                         @RequestParam BigDecimal price,
                                         @RequestParam int quantity,
                                         @RequestParam boolean isRecurring,
                                         @RequestParam int frequencyOfRecurrence) {
        Vendor vendor = this.getOne(id);
        return this.menuService.updateMenu(vendor, menuId, name, description, price,
                quantity, isRecurring, frequencyOfRecurrence);
    }

    //    DELETE menu
    @RequestMapping(value = "{id}/menus/{menuId}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteMenu(@PathVariable Long id,
                                         @PathVariable Long menuId) {
        Vendor vendor = this.getOne(id);
        this.menuService.delete(vendor, menuId);
    }

    /*get vendor order*/
    @GetMapping("{id}/orders")
    public @ResponseBody List<Order> getVendorOrder(@PathVariable Long id) {
        return this.orderService.findOrderByVendorId(this.getOne(id));
    }

    //    use it too for updating OrderStatus
    @RequestMapping(value = "{id}/orders/{orderId}/order-status", method = RequestMethod.PATCH)
    public @ResponseBody Order updateOrderOrderStatusId(
            @PathVariable Long id,
            @PathVariable Long orderId,
            @RequestParam int orderStatusId){
        Vendor vendor = this.vendorService.getOne(id);
        return this.orderService.updateOrderStatusId(vendor, orderId, orderStatusId);
    }

    @GetMapping("{id}/menus")
    public List<Menu> getMyMenu(@PathVariable Long id){
        return this.menuService.getVendorMenus(this.getOne(id));
    }

}
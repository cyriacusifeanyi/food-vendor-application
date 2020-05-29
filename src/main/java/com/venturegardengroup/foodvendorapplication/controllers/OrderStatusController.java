package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {
    @Autowired
    private OrderStatusService orderStatusService;

    //    view all
    @GetMapping()
    public List<OrderStatus> list() {
        return this.orderStatusService.list();
    }

    //    view one
    @GetMapping("{id}")
    public OrderStatus getOne(@PathVariable int id) {
        return this.orderStatusService.getOne(id);
    }

    @PostMapping()
    public OrderStatus create(@RequestParam String name) {
        return this.orderStatusService.create(name);
    }

    /*Don't use unless you are admin*/
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        this.orderStatusService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public OrderStatus update(@PathVariable int id,  @RequestParam String name) {
        return this.orderStatusService.update(id, name);
    }

    @RequestMapping(value = "{id}/orders")
    public List<Order> getOrders(@PathVariable int id){
        return this.orderStatusService.getOrders(id);
    }

}
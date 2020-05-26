package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderRepository orderRepository;

    //    view all
    @GetMapping()
    public List<OrderStatus> list() {
        return orderStatusRepository.findAll();
    }
    //    view one
    @GetMapping("{id}")
    public OrderStatus getOne(@PathVariable int id) {
        return orderStatusRepository.getOne(id);
    }

    @PostMapping()
    public OrderStatus create(@RequestParam String name) {
        return orderStatusRepository.save(new OrderStatus(name, new ArrayList<>()));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
//        delete order
        orderRepository.deleteOrderByOrderStatusId(getOne(id));
        orderStatusRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public OrderStatus update(@PathVariable int id,  @RequestParam String name) {

        OrderStatus existingOrderStatus = orderStatusRepository.getOne(id);
        existingOrderStatus.setName(name);
        return orderStatusRepository.saveAndFlush(existingOrderStatus);
    }

}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Order get(@PathVariable Long id) {
        return orderRepository.getOne(id);
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody final Order order){
        return orderRepository.saveAndFlush(order);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also, need to check for children records before deleting.
        orderRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Order update(@PathVariable Long id, @RequestBody Order order) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Order existingOrder = orderRepository.getOne(id);
        BeanUtils.copyProperties(order, existingOrder, "order_id");
        return orderRepository.saveAndFlush(existingOrder);
    }
}
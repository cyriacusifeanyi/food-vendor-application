package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import com.venturegardengroup.foodvendorapplication.services.OrderingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

//    @Autowired
//    private OrderingService orderingService;

    //    view all
    @GetMapping()
    public List<Order> list(Model model) {
        return orderRepository.findAll();
    }

    //    view one
    @GetMapping("{id}")
    public Order getOne(@PathVariable Long id) {
        return orderRepository.getOne(id);
    }


    @PostMapping("{id}/menus")
    public void addMenuToOrder(@PathVariable Long id, @RequestBody Long menuId){

        Order currentOrder = this.getOne(id);
        Menu newMenu = menuRepository.getOne(menuId);
        currentOrder.getMenus().add(newMenu);
        orderRepository.save(currentOrder);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Order update(@PathVariable Long id, @RequestBody Order order) {

        Order existingOrder = orderRepository.getOne(id);
        BeanUtils.copyProperties(order, existingOrder, "id");
        return orderRepository.saveAndFlush(existingOrder);
    }
}
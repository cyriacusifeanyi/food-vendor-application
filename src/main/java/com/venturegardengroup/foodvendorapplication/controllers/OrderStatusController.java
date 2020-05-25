package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
//@RequestMapping("/orderStatuses")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    //    view all
    @GetMapping("/order-statuses")
    public String list(Model model) {
        model.addAttribute("orderStatuses", orderStatusRepository.findAll());
        return "order-status/order-statuses";
    }
    //    view one
    @GetMapping("/order-statuses/{id}")
    public String getOne(Model model, @PathVariable int id) {
        model.addAttribute("orderStatus", orderStatusRepository.getOne(id));
        return "order-status/order-status";
    }
    //    add one
    @PostMapping("/order-status")
    public String create(@RequestParam String name) {
        orderStatusRepository.save(new OrderStatus(name, new ArrayList<>()));
        return "redirect:/order-statuses";
    }

}
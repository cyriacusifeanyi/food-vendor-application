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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;


    //    view all
    @GetMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("menus", menuRepository.findAll());
        model.addAttribute("orderStatuses", orderStatusRepository.findAll());

        return "order/orders";
    }
    //    view one
    @GetMapping("/orders/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        Order order = orderRepository.getOne(id);
        List<Menu> menus = menuRepository.findAll();
        menus.removeAll(order.getMenus());

        model.addAttribute("order", order);
        model.addAttribute("menus", menus);

        return "order/order";
    }

    //    used by only customer
    @Transactional
    @PostMapping("/order")
    public String create(@RequestParam Long customerId,
                         @RequestParam Long menuId,
                         @RequestParam int orderStatusId,
//                            @RequestParam String description,
                         @RequestParam BigDecimal amountDue,
                         @RequestParam BigDecimal amountPaid,
                         @RequestParam BigDecimal amountOutstanding){

        Customer customer = customerRepository.getOne(customerId);
        Menu menu = menuRepository.getOne(menuId);

        OrderStatus orderStatus = orderStatusRepository.getOne(orderStatusId);

        Order newOrder = new Order(
                customer,
                orderStatus,
                new ArrayList<>(),
//                description,
                amountDue,
                amountPaid,
                amountOutstanding,
                LocalDateTime.now()
        );
        newOrder.getMenus().add(menu);

        orderRepository.save(newOrder);
        return "redirect:/customers/" + customerId;
    }

    //    delete order - customer
    //required customer id, order_id

    //    view request (order) vendor and customer
    @GetMapping("/vendors/{id}/orders")
    public String viewOrders(Model model, @PathVariable Long id){
//        find all order where vendor is id
        List<Order> all = orderRepository.findAll();
//        search through all for vendorId {id}
//        model.addAttribute("orders", orderRepository.findAll());
        return "order/orders";
    }
//add new menu to order
// i think i should just change the number on the order table

    //    @PostMapping("/orders/{orderId}/menus/{menuId}")
    @PostMapping("/orders/{orderId}/add-menu")
    public String addMenuToOrder(@PathVariable Long orderId,
                                 @RequestParam Long menuId){
        Order currentOrder = orderRepository.getOne(orderId);
        Menu newMenu = menuRepository.getOne(menuId);
//        this did not work
//        newMenu.getOrders().add(currentOrder);
//        menuRepository.save(newMenu);

        currentOrder.getMenus().add(newMenu);
        orderRepository.save(currentOrder);
        return "redirect:/orders/" + orderId;
    }


}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/menus")
@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VendorRepository vendorRepository;

    //    view all
    @GetMapping("/menus")
    public String list(Model model) {
        model.addAttribute("menus", menuRepository.findAll());
//        the api doesnt neeed the vendor list, but the UI needs it when making selection
//        after i learn session this can be automatically added
//        model.addAttribute("vendors", vendorRepository.findAll());

        return "menu/menus";
    }

    //    view one
    @GetMapping("/menus/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        Menu menu = menuRepository.getOne(id);
        List<Order> orders = orderRepository.findAll();
        orders.removeAll(menu.getOrders());

        model.addAttribute("menu", menu);
        model.addAttribute("orders", orders);

        return "menu/menu";
    }

    //    create one is implemented in vendor controller - i will move it here later
//    create menu
    @PostMapping("/menu")
    public String createMenu(@RequestParam Long vendorId,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam BigDecimal price,
                             @RequestParam int quantity,
                             @RequestParam boolean isRecurring,
                             @RequestParam int frequencyOfRecurrence){
        Vendor vendor = vendorRepository.getOne(vendorId);

        Menu newMenu = new Menu(
                name, description, price, quantity,
                isRecurring, frequencyOfRecurrence,
                vendor, new ArrayList<>(), LocalDateTime.now()
        );
        menuRepository.save(newMenu);
        return "redirect:/menus";
    }

}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import com.venturegardengroup.foodvendorapplication.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;
    //    view all
    @GetMapping()
    public @ResponseBody List<Menu> list() {
        return this.menuService.list();
    }
    //    view one
    @GetMapping("{id}")
    public @ResponseBody Menu getOne(@PathVariable Long id) {
        return this.menuService.getOne(id);
    }

}
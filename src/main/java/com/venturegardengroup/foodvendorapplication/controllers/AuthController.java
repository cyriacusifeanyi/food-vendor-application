package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

//@RestController
//@RequestMapping("/auths")
@Controller
public class AuthController {
    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/auths")
    public String list(Model model) {
        model.addAttribute("auths", authRepository.findAll());
        return "auth/auths";
    }

    @GetMapping("/auths/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("auth", authRepository.getOne(id));
        return "auth/auth";
    }


}//i may need to remove this one to one connection between auth and other table
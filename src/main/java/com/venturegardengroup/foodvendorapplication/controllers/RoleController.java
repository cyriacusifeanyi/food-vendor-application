package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/roles")
    public String list(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/roles";
    }

    @PostMapping("/role")
    public String create(@RequestParam String name) {
        roleRepository.save(new Role(name, new ArrayList<>()));
        return "redirect:/roles";
    }

    @GetMapping("/roles/{id}")
    public String getOne(Model model, @PathVariable int id) {
        model.addAttribute("role", roleRepository.getOne(id));
        return "auth/role";
    }

    @PostMapping("/roles/{id}/auth")
    public String addAuth(@PathVariable int id,
                          @RequestParam String email,
                          @RequestParam String password){
        Role role = roleRepository.getOne(id);
        Auth auth = new Auth(
                email,
                password,
                role);
        authRepository.save(auth);
        return "redirect:/roles/" + id;
    }

}
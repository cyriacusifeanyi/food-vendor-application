package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auths")
public class AuthController {
    @Autowired
    private AuthRepository authRepository;

    @GetMapping()
    public List<Auth> list() {
        return authRepository.findAll();
    }

    @GetMapping("{id}")
    public Auth getOne(Model model, @PathVariable Long id) {
        return authRepository.getOne(id);
    }

    @Transactional
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        authRepository.deleteById(id);
    }

    @Transactional
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Auth update(@PathVariable long id, @RequestBody Auth auth) {

        Auth existingAuth = authRepository.getOne(id);
        BeanUtils.copyProperties(auth, existingAuth, "id");
        return authRepository.saveAndFlush(existingAuth);
    }


}
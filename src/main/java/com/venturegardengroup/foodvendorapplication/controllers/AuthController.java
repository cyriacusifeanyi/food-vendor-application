package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auths")
//@BasePathAwareController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping()
    public @ResponseBody List<Auth> list() {
        return this.authService.list();
    }

    @GetMapping("{id}")
    public Auth getOne(@PathVariable Long id) {
        return this.authService.getOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.authService.delete(id);
    }

    @GetMapping("{id}/role")
    public Role getRole(@PathVariable Long id){
        return this.authService.getRole(id);
    }

}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public List<Role> list() {
        return this.roleService.list();
    }

    @GetMapping("{id}")
    public Role getOne(@PathVariable int id) {
        return this.roleService.getOne(id);
    }

    @PostMapping()
    public Role create(@RequestParam String name) {
        return this.roleService.create(name);
    }

    /*Don't use unless you are admin*/
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        this.roleService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public Role update(@PathVariable int id, @RequestParam String name) {
        return this.roleService.update(id, name);
    }

    /*Handling related Auth resources below*/
    @GetMapping("{id}/auths")
    public List<Auth> findMyAuth(@PathVariable int id){
        return this.roleService.getAuthenticationList(id);
    }

    @PostMapping("{id}/auths")
    public Auth addAuth(@PathVariable int id,
                        @RequestParam String email,
                        @RequestParam String password){
        return roleService.addAuthentication(id, email, password);
    }
    @RequestMapping(value = "{id}/auths/{authId}", method = RequestMethod.PATCH)
    public Auth updateAuth(
            @PathVariable int id,
            @PathVariable Long authId,
            @RequestParam String email,
            @RequestParam String password) {
        return this.roleService.updateAuthentication(id, authId, email, password);
    }

}
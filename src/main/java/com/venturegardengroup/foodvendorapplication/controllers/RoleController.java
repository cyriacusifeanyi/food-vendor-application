package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;

    @GetMapping()
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @GetMapping("{id}")
    public Role getOne(@PathVariable int id) {
        return roleRepository.getOne(id);
    }

    @PostMapping()
    public Role create(@RequestParam String name) {
        return roleRepository.save(new Role(name, new ArrayList<>()));
    }

    @PostMapping("{id}/auths")
    public Auth addAuth(@PathVariable int id,
                          @RequestParam String email,
                          @RequestParam String password){
        Role role = roleRepository.getOne(id);
        Auth auth = new Auth(
                email,
                password,
                role);
        return authRepository.save(auth);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
//        delete auth where roleId
        authRepository.deleteAuthByRoleId(getOne(id));
        roleRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Role update(@PathVariable int id, @RequestParam String name) {

        Role existingRole = roleRepository.getOne(id);
        existingRole.setName(name);
        return roleRepository.saveAndFlush(existingRole);
    }
}
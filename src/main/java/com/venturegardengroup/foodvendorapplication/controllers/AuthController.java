package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auths")
public class AuthController {
    @Autowired
    private AuthRepository authRepository;

    @GetMapping
    public List<Auth> list() {
        return authRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Auth get(@PathVariable Long id) {
        return authRepository.getOne(id);
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public Auth create(@RequestBody final Auth auth){
        return authRepository.saveAndFlush(auth);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also, need to check for children records before deleting.
        authRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Auth update(@PathVariable Long id, @RequestBody Auth auth) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Auth existingAuth = authRepository.getOne(id);
        BeanUtils.copyProperties(auth, existingAuth, "auth_id");
        return authRepository.saveAndFlush(existingAuth);
    }
}
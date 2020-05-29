package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    public List<Role> list(){
        return roleRepository.findAll();
    }

    public Role getOne(int id){
        return this.roleRepository.getOne(id);
    }

    public Role create(String name){
        return roleRepository.saveAndFlush(
                new Role(name, new ArrayList<>()));
    }
    /*Don't use unless you are admin*/
    public void delete(int id){
        Role role = this.getOne(id);
        authService.deleteAuthByRoleId(role);
        roleRepository.deleteById(id);
    }

    public Role update(int id, String name){
        Role existingRole = this.getOne(id);
        existingRole.setName(name);
        return roleRepository.saveAndFlush(existingRole);
    }

    /*Handling Auth related repositories below*/
    public List<Auth> getAuthenticationList(int id){
        Role role = this.getOne(id);
        return authService.findAllByRoleId(role);
    }

    public Auth addAuthentication(int id, String email, String password){
        Role role = this.getOne(id);
        return this.authService.addAuthentication(email, password, role);
    }

    public Auth updateAuthentication(int id, Long authId, String email, String password){
        Role role = this.getOne(id);
        return authService.updateAuthentication(role, authId, email, password);
    }

}

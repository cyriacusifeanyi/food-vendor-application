package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private RoleService roleService;

    public List<Auth> list(){
        return authRepository.findAll();
    }

    public Auth getOne(Long id){
        return authRepository.getOne(id);
    }

    public void delete(Long id){
        authRepository.deleteById(id);
    }

    public Role getRole(Long id){
        Auth auth = this.getOne(id);
        return roleService.getOne(
                Objects.requireNonNull(auth.getRoleId().getId()));
    }

    //    user by RoleService
    public List<Auth> findAllByRoleId(Role role){
        return authRepository.findAllByRoleId(role);
    }

    public void deleteAuthByRoleId(Role role){
        authRepository.deleteAuthByRoleId(role);
    }

    public Auth addAuthentication(String email, String password, Role role){
        Auth auth = new Auth(email, password, role);
        return authRepository.saveAndFlush(auth);
    }

    public Auth updateAuthentication(Role role, Long authId, String email, String password){
        Auth existingAuth = authRepository.getOne(authId);
        if (Objects.equals(role.getId(), existingAuth.getRoleId().getId())){
            existingAuth.setEmail(email);
            existingAuth.setPassword(password);
            return authRepository.saveAndFlush(existingAuth);
        }else {
            return authRepository.saveAndFlush(existingAuth);
        }
    }
    /*end for role*/



}

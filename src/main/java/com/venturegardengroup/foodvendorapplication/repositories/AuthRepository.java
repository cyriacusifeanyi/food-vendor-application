package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepository extends JpaRepository<Auth,Long> {
    void deleteAuthByRoleId(Role roleId);
    List<Auth> findAllByRoleId(Role roleId);
}

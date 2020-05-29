package com.venturegardengroup.foodvendorapplication.repositories;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> getMenuByVendorId(Vendor vendorId);
}

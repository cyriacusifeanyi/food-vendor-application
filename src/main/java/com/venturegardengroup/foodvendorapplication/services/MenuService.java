package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> list() {
        return menuRepository.findAll();
    }

    public Menu getOne(Long id) {
        return this.menuRepository.getOne(id);
    }

    /* Vendor create menu*/
    public Menu create(Vendor vendor,
                       String name,
                       String description,
                       BigDecimal price,
                       int quantity,
                       boolean isRecurring,
                       int frequencyOfRecurrence) {

        Menu newMenu = new Menu(
                name, description, price, quantity,
                isRecurring, frequencyOfRecurrence,
                vendor, new ArrayList<>(), LocalDateTime.now()
        );
        return menuRepository.saveAndFlush(newMenu);
    }

    //    Vendor PATCH menu
    public Menu updateMenu(@NotNull Vendor vendor,
                           @NotNull Long menuId,
                           String name,
                           String description,
                           BigDecimal price,
                           int quantity,
                           boolean isRecurring,
                           int frequencyOfRecurrence) {

        Menu existingMenu = this.getOne(menuId);
        Menu newMenu = new Menu(
                name, description, price, quantity,
                isRecurring, frequencyOfRecurrence,
                vendor, existingMenu.getOrders(), existingMenu.getDateTimeCreated()
        );

        if (Objects.equals(vendor.getId(), existingMenu.getVendorId().getId())) {
            BeanUtils.copyProperties(newMenu, existingMenu, "id");
            return menuRepository.saveAndFlush(existingMenu);
        } else {
            return this.getOne(menuId);
        }
    }

    public void delete(@NotNull Vendor vendor, Long menuId){
        Menu currentMenu = this.getOne(menuId);
        if (Objects.equals(vendor.getId(), currentMenu.getVendorId().getId())){
            this.menuRepository.deleteById(menuId);
            System.out.println("Deletion: successful");
        }else{
            System.out.println("Deletion: unsuccessful");
        }
    }

    public List<Menu> getVendorMenus(Vendor vendor){
        return this.menuRepository.getMenuByVendorId(vendor);
    }
}
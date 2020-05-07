package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.repositories.MenuRepository;
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
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public List<Menu> list() {
        return menuRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Menu get(@PathVariable Long id) {
        return menuRepository.getOne(id);
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public Menu create(@RequestBody final Menu menu){
        return menuRepository.saveAndFlush(menu);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also, need to check for children records before deleting.
        menuRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Menu update(@PathVariable Long id, @RequestBody Menu menu) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Menu existingMenu = menuRepository.getOne(id);
        BeanUtils.copyProperties(menu, existingMenu, "menu_id");
        return menuRepository.saveAndFlush(existingMenu);
    }
}
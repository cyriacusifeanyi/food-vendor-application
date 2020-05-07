package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
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
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping
    public List<Vendor> list() {
        return vendorRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Vendor get(@PathVariable Long id) {
        return vendorRepository.getOne(id);
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public Vendor create(@RequestBody final Vendor vendor){
        return vendorRepository.saveAndFlush(vendor);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also, need to check for children records before deleting.
        vendorRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Vendor update(@PathVariable Long id, @RequestBody Vendor vendor) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Vendor existingVendor = vendorRepository.getOne(id);
        BeanUtils.copyProperties(vendor, existingVendor, "vendor_id");
        return vendorRepository.saveAndFlush(existingVendor);
    }
}
package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> list(){
        return vendorRepository.findAll();
    }

    public Vendor getOne(Long id){
        return this.vendorRepository.getOne(id);
    }

    public void delete(Long id){
        vendorRepository.deleteById(id);
    }

    public Vendor create(String businessName, String phoneNumber){

        Vendor vendor = new Vendor(
                businessName, phoneNumber,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now());
        return vendorRepository.saveAndFlush(vendor);
    }

}

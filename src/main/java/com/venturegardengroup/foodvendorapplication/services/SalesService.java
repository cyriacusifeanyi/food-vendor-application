package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class SalesService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VendorService vendorService;

    public List<List<String>> generateVendorDailySalesReport(Long id){

        List<Order> todayOrder = this.orderRepository.findByVendorIdAndDateCreated(
                this.vendorService.getOne(id),
                LocalDate.now());

        List<List<String>> salesReport =new ArrayList<>();
        int j = 0;
        for (Order order:todayOrder) {
            List<String> list1 =new ArrayList<>();
            int i =0;

            for (Menu menu: order.getMenus()){
                list1.add(i, menu.getName());
                list1.add(i, String.valueOf(menu.getPrice()));
                list1.add(i, menu.getDescription());
                i++;
            }

            salesReport.add(j, list1);
            salesReport.add(j, Collections.singletonList(order.getAmountDue().toString()));
            salesReport.add(j, Collections.singletonList(order.getAmountOutstanding().toString()));
            salesReport.add(j, Collections.singletonList(order.getVendorId().getBusinessName()));
            j++;
        }

        return salesReport;
    }


}

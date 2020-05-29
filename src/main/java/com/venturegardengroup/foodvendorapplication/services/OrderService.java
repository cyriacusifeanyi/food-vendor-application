package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Menu;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private MenuService menuService;


    public List<Order> list(){
        return this.orderRepository.findAll();
    }

    public Order getOne(Long id){
        return this.orderRepository.getOne(id);
    }

    /*Beginning of VendorController*/
    public List<Order> findOrderByVendorId(Vendor vendor){
        return orderRepository.findByVendorId(vendor);
    }

    public Order updateOrderStatusId(@NotNull Vendor vendor, Long orderId, int orderStatusId){

        Order existingOrder = this.getOne(orderId);
        if (Objects.equals(vendor.getId(), existingOrder.getVendorId().getId())){

            OrderStatus orderStatus = this.orderStatusService.getOne(orderStatusId);
            existingOrder.setOrderStatusId(orderStatus);
            return orderRepository.saveAndFlush(existingOrder);
        }else {
            return existingOrder;
        }
    }
    /*End of VendorController*/


    /*Beginning of CustomerController*/
    public Order create(@NotNull Customer customer,
                        Long menuId,
                        int orderStatusId,
                        BigDecimal amountPaid){
        Menu menu = this.menuService.getOne(menuId);
        Vendor vendor = menu.getVendorId();
        BigDecimal amountDue = menu.getPrice();
        BigDecimal amountOutstanding = amountDue.subtract(amountPaid);
        OrderStatus orderStatus = this.orderStatusService.getOne(orderStatusId);

        Order newOrder = new Order(
                customer,
                vendor,
                orderStatus,
                new ArrayList<>(),
                amountDue,
                amountPaid,
                amountOutstanding,
                LocalDate.now(),
                LocalTime.now()
        );
        newOrder.getMenus().add(menu);

        return orderRepository.saveAndFlush(newOrder);
    }

    public Order update(@NotNull Customer customer,
                        Long orderId,
                        Order order){
        Order currentOrder = this.getOne(orderId);

//        if customer is the owner of this notification he can update
        if (Objects.equals(customer.getId(), currentOrder.getCustomerId().getId())){
            Order existingOrder = this.getOne(orderId);
            BeanUtils.copyProperties(order, existingOrder, "id");
            return this.orderRepository.saveAndFlush(existingOrder);
        }else {
            return this.orderRepository.saveAndFlush(currentOrder);
        }
    }

    public void cancel(@NotNull Customer customer, Long orderId){
        Order currentOrder = this.getOne(orderId);
        if (Objects.equals(customer.getId(), currentOrder.getCustomerId().getId())){
            this.orderRepository.deleteById(orderId);
        }
    }

    public Order addMenuToOrder(Long orderId, Long menuId){

        Order order = this.getOne(orderId);
        Menu newMenu = menuService.getOne(menuId);
        order.getMenus().add(newMenu);
        return orderRepository.saveAndFlush(order);
    }

    /*End of CustomerController*/


}

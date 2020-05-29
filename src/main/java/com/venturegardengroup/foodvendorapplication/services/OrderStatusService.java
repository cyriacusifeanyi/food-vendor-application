package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.OrderStatus;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderStatus> list(){
        return orderStatusRepository.findAll();
    }

    public OrderStatus getOne(int id){
        return orderStatusRepository.getOne(id);
    }

    public OrderStatus create(String name){
        return orderStatusRepository.saveAndFlush(
                new OrderStatus(name, new ArrayList<>()));
    }

    /*Don't use unless you are admin*/
    public void delete(int id){
        orderRepository.deleteOrderByOrderStatusId(this.getOne(id));
        orderStatusRepository.deleteById(id);
    }

    public OrderStatus update(int id, String name){
        OrderStatus existingOrderStatus = this.getOne(id);

        existingOrderStatus.setName(name);
        return orderStatusRepository.saveAndFlush(existingOrderStatus);
    }

    public List<Order> getOrders(int id){
        OrderStatus orderStatus = this.getOne(id);
        return orderRepository.findAllByOrderStatusId(orderStatus);
    }

    public Order updateOrder(@NotNull Customer customer,
                             Long orderId,
                             Order order){
        Order currentOrder = this.orderRepository.getOne(orderId);

//        if customer is the owner of this notification he can update
        if (Objects.equals(customer.getId(), currentOrder.getCustomerId().getId())){
            Order existingOrder = this.orderRepository.getOne(orderId);
            BeanUtils.copyProperties(order, existingOrder, "id");
            return this.orderRepository.saveAndFlush(existingOrder);
        }else {
            return this.orderRepository.saveAndFlush(currentOrder);
        }
    }

}

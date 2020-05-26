package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderStatusService {
    @Autowired
    private OrderRepository orderRepository;

    public void makeOrder(){}

    public void cancelOrder(){}

    public void updateOrder(){}

//    public void transferFunds(String from, String to, Integer amount) {
//        Account accountFrom = this.accountRepository.findByIban(from);
//        Account accountTo = this.accountRepository.findByIban(to);
//
//        accountFrom.setBalance(accountFrom.getBalance() - amount);
//        accountTo.setBalance(accountTo.getBalance() + amount);
//    }

}

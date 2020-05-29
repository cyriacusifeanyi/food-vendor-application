package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.NotificationStatus;
import com.venturegardengroup.foodvendorapplication.models.Order;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private NotificationStatusService notificationStatusService;

    public List<Notification> list(){
        return notificationRepository.findAll();
    }

    public Notification getOne(Long id){
        return this.notificationRepository.getOne(id);
    }

    /*Don't use unless you are admin*/
    public void delete(Long id){
        notificationRepository.deleteById(id);
    }

    /*only used by a customer */
    public List<Notification> findByCustomerId(Customer customer){
        return notificationRepository.findByCustomerId(customer);
    }

    //    create notification
    public Notification createNotification(Vendor vendor, Long orderId,
                                           int notificationStatusId, String message){

        Order order = this.orderService.getOne(orderId);
        Customer customer = order.getCustomerId();
        NotificationStatus notificationStatus = this.notificationStatusService.getOne(notificationStatusId);

        Notification newNotification = new Notification(
                vendor,
                customer,
                order,
                notificationStatus,
                message,
                LocalDateTime.now()
        );
        return notificationRepository.saveAndFlush(newNotification);
    }

    //    update notification
    public Notification updateNotification(@NotNull Vendor vendor,
                                           Long notificationId,
                                           String message){
        Notification currentNotification = notificationRepository.getOne(notificationId);

        //        if vendor is the owner of this notification he can update
        if (Objects.equals(vendor.getId(), currentNotification.getVendorId().getId())){
            Notification existingNotification = notificationRepository.getOne(notificationId);
            existingNotification.setMessage(message);
            return notificationRepository.saveAndFlush(existingNotification);
        }else {
            return currentNotification;
        }
    }

    public Notification updateNotificationStatusId(@NotNull Customer customer,
                                                   Long notificationId,
                                                   int notificationStatusId){
        Notification existingNotification = this.getOne(notificationId);
        if (Objects.equals(customer.getId(), existingNotification.getVendorId().getId())){
            NotificationStatus notificationStatus = this.notificationStatusService.getOne(notificationStatusId);
            existingNotification.setNotificationStatusId(notificationStatus);
            return notificationRepository.saveAndFlush(existingNotification);
        }else {
            return existingNotification;
        }
    }
}

package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.NotificationStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationStatusService {
    @Autowired
    private MessageStatusRepository messageStatusRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationStatus> list(){
        return messageStatusRepository.findAll();
    }

    public NotificationStatus getOne(int id){
        return messageStatusRepository.getOne(id);
    }

    public NotificationStatus create(String name){
        return messageStatusRepository.saveAndFlush(
                new NotificationStatus(
                        name,
                        new ArrayList<>()));
    }

    /*Don't use unless you are admin*/
    public void delete(int id){
        notificationRepository.deleteNotificationByNotificationStatusId(this.getOne(id));
        messageStatusRepository.deleteById(id);
    }

    public NotificationStatus update(int id, String name){
        NotificationStatus existingNotificationStatus = this.getOne(id);

        existingNotificationStatus.setName(name);
        return messageStatusRepository.saveAndFlush(existingNotificationStatus);
    }

    public List<Notification> getNotifications(int id) {
        NotificationStatus notificationStatus = this.getOne(id);
        return notificationRepository.findAllByNotificationStatusId(notificationStatus);
    }

}

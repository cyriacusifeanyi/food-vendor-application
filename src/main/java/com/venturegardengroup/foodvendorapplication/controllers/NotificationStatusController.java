package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.NotificationStatus;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.services.NotificationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification-statuses")
public class NotificationStatusController {
    @Autowired
    private NotificationStatusService notificationStatusService;

    //    view all
    @GetMapping()
    public List<NotificationStatus> list() {
        return this.notificationStatusService.list();
    }

    @GetMapping("{id}")
    public NotificationStatus getOne(@PathVariable int id) {
        return this.notificationStatusService.getOne(id);
    }

    @PostMapping()
    public NotificationStatus create(@RequestParam String name) {
        return notificationStatusService.create(name);
    }

    /*Don't use unless you are admin*/
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        this.notificationStatusService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public NotificationStatus update(@PathVariable int id, @RequestParam String name) {
        return this.notificationStatusService.update(id, name);
    }

    @GetMapping("{id}/notifications")
    public List<Notification> getNotifications(@PathVariable int id){
        return this.notificationStatusService.getNotifications(id);
    }

}
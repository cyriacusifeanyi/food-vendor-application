package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import com.venturegardengroup.foodvendorapplication.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message-statuses")
public class MessageStatusController {
    @Autowired
    private MessageStatusRepository messageStatusRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    //    view all
    @GetMapping()
    public List<MessageStatus> list() {
        return messageStatusRepository.findAll();
    }

    @GetMapping("{id}")
    public MessageStatus getOne(@PathVariable int id) {
        return messageStatusRepository.getOne(id);
    }

    @PostMapping()
    public MessageStatus create(@RequestParam String name) {
        MessageStatus newMessageStatus = new MessageStatus(name, new ArrayList<>());
        return messageStatusRepository.save(newMessageStatus);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        notificationRepository.deleteNotificationByMessageStatusId(getOne(id));
        messageStatusRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageStatus update(@PathVariable int id, @RequestParam String name) {

        MessageStatus existingMessageStatus = messageStatusRepository.getOne(id);
        existingMessageStatus.setName(name);
        return messageStatusRepository.saveAndFlush(existingMessageStatus);
    }
}
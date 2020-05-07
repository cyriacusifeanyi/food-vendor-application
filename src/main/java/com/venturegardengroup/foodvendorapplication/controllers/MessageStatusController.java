package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
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
@RequestMapping("/messageStatuses")
public class MessageStatusController {
    @Autowired
    private MessageStatusRepository messageStatusRepository;

    @GetMapping
    public List<MessageStatus> list() {
        return messageStatusRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public MessageStatus get(@PathVariable Long id) {
        return messageStatusRepository.getOne(id);
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public MessageStatus create(@RequestBody final MessageStatus messageStatus){
        return messageStatusRepository.saveAndFlush(messageStatus);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also, need to check for children records before deleting.
        messageStatusRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageStatus update(@PathVariable Long id, @RequestBody MessageStatus messageStatus) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        MessageStatus existingMessageStatus = messageStatusRepository.getOne(id);
        BeanUtils.copyProperties(messageStatus, existingMessageStatus, "messageStatus_id");
        return messageStatusRepository.saveAndFlush(existingMessageStatus);
    }
}
package com.venturegardengroup.foodvendorapplication.controllers;

import com.venturegardengroup.foodvendorapplication.models.MessageStatus;
import com.venturegardengroup.foodvendorapplication.models.Role;
import com.venturegardengroup.foodvendorapplication.repositories.MessageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

//@RestController
//@RequestMapping("/messageStatuses")
@Controller
public class MessageStatusController {
    @Autowired
    private MessageStatusRepository messageStatusRepository;
    //    view all
    @GetMapping("/message-statuses")
    public String list(Model model) {
        model.addAttribute("messageStatuses", messageStatusRepository.findAll());
        return "message-status/message-statuses";
    }
    //    view one
    @GetMapping("/message-statuses/{id}")
    public String getOne(Model model, @PathVariable int id) {
        model.addAttribute("messageStatus", messageStatusRepository.getOne(id));
        return "message-status/message-status";
    }
    //    add one
    @PostMapping("/message-status")
    public String create(@RequestParam String name) {
        messageStatusRepository.save(new MessageStatus(name, new ArrayList<>()));
        return "redirect:/message-statuses";
    }
}
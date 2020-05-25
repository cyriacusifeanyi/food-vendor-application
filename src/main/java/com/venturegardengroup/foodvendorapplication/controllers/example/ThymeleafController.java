//package com.venturegardengroup.foodvendorapplication.controllers.example;
//
//import com.venturegardengroup.foodvendorapplication.models.example.Event;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class ThymeleafController {
//
////    @GetMapping("/thymeleaf")
////    public String home(Model model) {
////        model.addAttribute("fullName", "Cyriacus Ifeanyi");
////        return "index";
////    }
//
////    @GetMapping("/object")
////    public String eventObject(Model model) {
////        model.addAttribute("event", new Event("AI Saturday Lagos"));
////        return "index";
////    }
//
//    private List<Event> eventList;
//
//    public ThymeleafController() {
//        this.eventList = new ArrayList<>();
//        this.eventList.add(new Event("James Gosling"));
//        this.eventList.add(new Event("Martin Odersky"));
//    }
//
//    @GetMapping("/objectList")
//    public String home(Model model) {
//        model.addAttribute("list", eventList);
//        return "/main/resources/templates/example/index.html";
//    }
//
//    private String name;
//    private String message;
//
//    @GetMapping("/message")
//    public String messagePage(Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("message", message);
//        return "/main/resources/templates/example/message.html";
//    }
//
//    @PostMapping("/")
//    public String post(@RequestParam String name, @RequestParam String message) {
//        this.name = name;
//        this.message = message;
//        return "redirect:/message";
//    }
//
//}
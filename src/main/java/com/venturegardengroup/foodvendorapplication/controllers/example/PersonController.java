//package com.venturegardengroup.foodvendorapplication.controllers.example;
//
//import com.venturegardengroup.foodvendorapplication.models.example.Person;
//import com.venturegardengroup.foodvendorapplication.repositories.example.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
////@RestController
////@RequestMapping("/auths")
//@Controller
//public class PersonController {
//    @Autowired
//    private PersonRepository personRepository;
//
//    @GetMapping("/persons")
//    public String list(Model model) {
//        model.addAttribute("list", personRepository.findAll());
//        return "/main/resources/templates/example/persons.html";
//    }
//
//    @PostMapping("/person")
//    public String create(@RequestParam String name, @RequestParam String message) {
//        personRepository.save(new Person(name,message));
//        return "redirect:/persons";
//    }
//
//    @GetMapping("/persons/{id}")
//    public String getOne(Model model, @PathVariable Long id) {
//        model.addAttribute("person", personRepository.getOne(id));
//        return "/main/resources/templates/example/person.html";
//    }
//
//}
//package com.venturegardengroup.foodvendorapplication.resources.example;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/rest/hello")
//@RestController
//public class HelloResource {
//
//    @GetMapping("/all")
//    public String hello(){
//        return  "hello youtube";
//    }
//
////    both authentication and authorization are required.
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping("secured/all")
//    public String secureHello(){
//        return "secured hello";
//    }
//
////    authentication need but not authorization
//    @GetMapping("secured/alternate")
//    public String alternate(){
//        return "alternate";
//    }
//
//}

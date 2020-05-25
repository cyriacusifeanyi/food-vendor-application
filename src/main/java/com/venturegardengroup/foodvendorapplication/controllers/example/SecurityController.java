//package com.venturegardengroup.foodvendorapplication.controllers.example;
//
//import com.venturegardengroup.foodvendorapplication.services.example.AnalyticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.BasePathAwareController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@BasePathAwareController
//public class SecurityController {
//
//    @Autowired
//    private AnalyticsService analyticsService;
//
//    @RequestMapping("/security")
//    @ResponseBody
//    public String count() {
//        int visits = analyticsService.incrementAndCount();
//
//        if (visits > 1){
//            return "Hello again - (Visits: " + visits+")";
//        }else {
//            return "Hello There, Welcome to FVA";
//        }
//    }
//
//
//}

//package com.venturegardengroup.foodvendorapplication.temp_trash.oldsample;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/notifications")
//public class NotificationController {
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @GetMapping
//    public List<Notification> list() {
//        return notificationRepository.findAll();
//    }
//
//    @GetMapping
//    @RequestMapping("{id}")
//    public Notification get(@PathVariable Long id) {
//        return notificationRepository.getOne(id);
//    }
//    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
//    public Notification create(@RequestBody final Notification notification){
//        return notificationRepository.saveAndFlush(notification);
//    }
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable Long id) {
//        //Also, need to check for children records before deleting.
//        notificationRepository.deleteById(id);
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
//    public Notification update(@PathVariable Long id, @RequestBody Notification notification) {
//        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need...
//        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
//        Notification existingNotification = notificationRepository.getOne(id);
//        BeanUtils.copyProperties(notification, existingNotification, "notification_id");
//        return notificationRepository.saveAndFlush(existingNotification);
//    }
//}
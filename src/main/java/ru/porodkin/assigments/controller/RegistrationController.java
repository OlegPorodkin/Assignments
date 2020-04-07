package ru.porodkin.assigments.controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import ru.porodkin.assigments.domain.User;
//import ru.porodkin.assigments.repository.UserRepository;
//import ru.porodkin.assigments.service.UserService;
//
//import java.util.Map;
//
//@Controller
//public class RegistrationController {
//
//    private final UserService userService;
//
//    @Autowired
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//
////    @GetMapping
////    public String hello(){
////        return "login";
////    }
//
//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String saveNewUser(User user, Map<String, String> model){
//
//        if (!userService.addUser(user)) {
//            model.put("massage", "User with name"+user.getUsername()+"already exist !");
//        }
//
//        return "redirect:/login";
//    }
//}

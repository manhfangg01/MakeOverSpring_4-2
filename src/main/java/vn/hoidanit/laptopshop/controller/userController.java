package vn.hoidanit.laptopshop.controller;
// this file is the primary part of Model MVC spring: main function is charge

// for redirecting your website

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.controller.*;

@Controller
public class userController { // Model MVC code
    private UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/") // Sufiex of your ur Ex: localhost:8080/gayy to access resource in
                         // localhost:8080
    public String getHomePage() {
        String test = this.userService.handleHello(); // static data is unusable in this case
        return "hello"; // file trả về sẽ là thứ được hiện lên khi hàm này chạy -> cần dùng String
    }
}

// @RestController // Model Restful API
// public class userController {

// private UserService userService;

// public userController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }

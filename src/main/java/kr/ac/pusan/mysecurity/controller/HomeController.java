package kr.ac.pusan.mysecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "/home";
    }


    @GetMapping("/user")
    public String user(){
        return "/pages/user";
    }

    @GetMapping("/manager")
    public String manager(){
        return "/pages/manager";
    }

}

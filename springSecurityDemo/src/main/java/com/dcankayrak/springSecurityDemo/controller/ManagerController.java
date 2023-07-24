package com.dcankayrak.springSecurityDemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class ManagerController {

    @GetMapping
    public String managerMessage(){
        return "GET -> Manager";
    }
    @PostMapping
    public String managerPostMessage(){
        return "POST -> Manager";
    }
    @PutMapping
    public String managerPutMessage(){
        return "PUT -> Manager";
    }
    @DeleteMapping
    public String managerDeleteMessage(){
        return "DELETE -> Manager";
    }
}

package com.dcankayrak.springSecurityDemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String adminMessage(){
        return "GET -> Admin";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String adminPostMessage(){
        return "POST -> Admin";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String adminPutMessage(){
        return "PUT -> Admin";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String adminDeleteMessage(){
        return "DELETE -> Admin";
    }

}

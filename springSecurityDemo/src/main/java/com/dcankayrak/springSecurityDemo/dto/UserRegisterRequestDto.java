package com.dcankayrak.springSecurityDemo.dto;

import com.dcankayrak.springSecurityDemo.entities.enums.Role;
import lombok.Data;

@Data
public class UserRegisterRequestDto {

    private String username;
    private String password;
    private Role role;
}

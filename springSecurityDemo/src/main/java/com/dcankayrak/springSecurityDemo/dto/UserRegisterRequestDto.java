package com.dcankayrak.springSecurityDemo.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {

    private String username;
    private String password;
}

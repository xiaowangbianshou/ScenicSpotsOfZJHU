package com.campus.springboot.controller.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
}
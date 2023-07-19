package com.campus.springboot.controller.dto;

import lombok.Data;

/*
 * 接收登录请求的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
    private String classname;
    private String studentid;
}

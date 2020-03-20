package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/")
public class LoginController {
    /*
    功能：登录
    参数:username,password
     */
    private UserService userService;
    public String loginIn(){
        return null;

    }
}

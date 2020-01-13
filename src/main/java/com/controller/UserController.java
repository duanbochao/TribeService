package com.controller;

import com.service.UserService;
import com.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/7 12:54
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/currentUserName")
    public String currentUserName(){
        return UserUtils.getCurrentUser().getNickname();
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin(){
        List<GrantedAuthority> authorities = UserUtils.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")){
                return true;
            }
        }
            return false;
    }
}

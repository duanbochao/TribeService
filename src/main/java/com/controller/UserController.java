package com.controller;

import com.service.UserService;
import com.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

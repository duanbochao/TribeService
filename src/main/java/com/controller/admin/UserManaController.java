package com.controller.admin;

import com.bean.Role;
import com.bean.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/15 16:49
 */
@RestController
@RequestMapping("/admin")
public class UserManaController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public List<User> getUserByNickname(String nickName){
        return userService.getUserByNickname(nickName);
    }

    @RequestMapping("/roles")
    public List<Role> getAllRole(){
        return userService.getAllRole();
    }


}

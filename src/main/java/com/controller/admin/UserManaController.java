package com.controller.admin;

import com.bean.RespBean;
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


    @RequestMapping("/user/enabled")
    public RespBean updatUserEnabled(Boolean enabled,Integer uid){
        Integer integer = userService.updatUserEnabled(enabled, uid);
        if (integer>0){
            return new RespBean("success","更新成功");
        }
        return new RespBean("error","更新失败");
    }


    @RequestMapping("/user/delete")
    public RespBean deleteUserByUid(Integer uid){
        Integer integer = userService.deleteUserByUid(uid);
        if (integer>0){
            return new RespBean("success","删除成功");
        }
        return new RespBean("error","删除失败");
    }
}

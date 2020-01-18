package com.service;

import com.bean.Role;
import com.bean.User;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/6 20:40
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            return new User();
        }

        List<Role> roles = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roles);

        return user;
    }


    public List<User> getUserByNickname(String nickName){
        return userMapper.getUserByNickname(nickName);
    }

    public List<Role> getAllRole(){
        return userMapper.getAllRole();
    }
}

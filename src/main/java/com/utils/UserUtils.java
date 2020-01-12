package com.utils;

import com.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/7 12:55
 */
public class UserUtils {

    public static User getCurrentUser(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

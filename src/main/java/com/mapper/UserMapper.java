package com.mapper;

import com.bean.Role;
import com.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/6 20:41
 */
public interface UserMapper {

    User loadUserByUsername(@Param("username") String username);

   List<User> getUserByNickname(String nickName);

    List<Role> getAllRole();

    Integer updatUserEnabled(Boolean enabled,Integer uid);

    Integer deleteUserByUid(Integer uid);
}

package com.mapper;

import com.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/6 20:41
 */
public interface UserMapper {

    User loadUserByUsername(@Param("username") String username);

}

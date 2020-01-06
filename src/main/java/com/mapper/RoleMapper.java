package com.mapper;

import com.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/6 20:44
 */
public interface RoleMapper {

    List<Role> getRolesByUid(@Param("uid") Integer uid);
}

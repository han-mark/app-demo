package com.bird.business.dao;

import com.bird.business.domain.Menus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface AdminManagerDao {
    /**
     * 根据用户名获取角色列表
     * @param userName
     * @return
     */
    public Set<String> getRolesByUsername(@Param("userName") String userName);

    /**
     * 根据用户名获取权限列表
     * @param userName
     * @return
     */
    public Set<String> getPermsByRoles(@Param("userName") String userName);

    /**
     * 根据用户获取菜单列表
     * @param userName
     * @return
     */
    public List<Menus> getMenusList(@Param("userName") String userName);
}
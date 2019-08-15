package com.bird.business.service;

import com.bird.business.domain.Menus;
import com.bird.business.domain.TbMenus;

import java.util.List;
import java.util.Set;

public interface IAdminManagerService {
    public Set<String> getRolesByUsername(String userName);
    public Set<String> getPermsByRoles(String userName);
    public List<Menus> getMenusList(String userName);
    public List<TbMenus> getTreeMenusList(Long roleId);
}
package com.bird.business.service.impl;

import com.bird.business.dao.AdminManagerDao;
import com.bird.business.dao.TbMenusMapper;
import com.bird.business.dao.TbRolesMenusMapper;
import com.bird.business.domain.*;
import com.bird.business.service.IAdminManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminManagerServiceImpl implements IAdminManagerService {

    @Autowired
    private AdminManagerDao adminManagerDao;

    @Autowired
    private TbMenusMapper tbMenusMapper;

    @Autowired
    private TbRolesMenusMapper tbRolesMenusMapper;

    @Override
    public Set<String> getRolesByUsername(String userName) {
        return adminManagerDao.getRolesByUsername(userName);
    }

    @Override
    public Set<String> getPermsByRoles(String userName) {
        return adminManagerDao.getPermsByRoles(userName);
    }

    @Override
    public List<Menus> getMenusList(String userName) {
        //左侧导航栏内容,过滤掉权限数据(只做到支持二级菜单)
        List<Menus> results = new ArrayList<>();
        List<Menus> menusList = adminManagerDao.getMenusList(userName);
        if (!CollectionUtils.isEmpty(menusList)) {
            for (Menus menus : menusList) {
                //一级菜单
                if (menus.getParentId() == 0) {
                    //二级菜单
                    List<Menus> cMenusList = new ArrayList<>();
                    for (Menus menus1 : menusList) {
                        if (menus1.getParentId() == menus.getMenuId()) {
                            cMenusList.add(menus1);
                        }
                    }
                    menus.setChildren(cMenusList);
                    results.add(menus);
                }
            }
        }
        return results;
    }

    @Override
    public List<TbMenus> getTreeMenusList(Long roleId) {
        //获取所有的菜单列表(包含权限),用于菜单列表和编辑角色权限
        TbMenusExample tbMenusExample = new TbMenusExample();
        tbMenusExample.setOrderByClause("sorting desc");
        List<TbMenus> tbMenusList = tbMenusMapper.selectByExample(tbMenusExample);
        if (!(roleId == -1)){
            TbRolesMenusExample tbRolesMenusExample = new TbRolesMenusExample();
            tbRolesMenusExample.or().andRoleIdEqualTo(roleId);
            List<TbRolesMenusKey> tbRolesMenusKeys = tbRolesMenusMapper.selectByExample(tbRolesMenusExample);
            //没有单独写mapper,使用生成的代码方法
            if (!CollectionUtils.isEmpty(tbRolesMenusKeys)) {
                for(TbMenus tbMenus : tbMenusList){
                    for (TbRolesMenusKey tbRolesMenusKey : tbRolesMenusKeys) {
                        if (tbRolesMenusKey.getMenuId() == tbMenus.getMenuId()) {
                            tbMenus.setChecked(true);
                        }
                    }
                }
            }
        }
        return tbMenusList;
    }
}

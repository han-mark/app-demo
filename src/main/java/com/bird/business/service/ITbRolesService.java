package com.bird.business.service;

import com.bird.business.domain.TbRoles;
import com.bird.business.domain.TbRolesExample;
import com.bird.business.domain.TbRolesMenusKey;

import java.util.List;

public interface ITbRolesService {
    public long countByExample(TbRolesExample example);

    public int deleteByExample(TbRolesExample example);

    public int deleteByPrimaryKey(Long roleId);

    public int insert(TbRoles record);

    public int insertSelective(TbRoles record);

    public List<TbRoles> selectByExample(TbRolesExample example);

    public TbRoles selectByPrimaryKey(Long roleId);

    public int updateByExampleSelective(TbRoles record,TbRolesExample example);

    public int updateByExample(TbRoles record,TbRolesExample example);

    public int updateByPrimaryKeySelective(TbRoles record);

    public int updateByPrimaryKey(TbRoles record);

    public void addRolesMenus(Long roleId, List<TbRolesMenusKey> list);
}
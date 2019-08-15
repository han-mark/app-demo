package com.bird.business.service;

import com.bird.business.domain.TbMenus;
import com.bird.business.domain.TbMenusExample;
import java.util.List;

public interface ITbMenusService {
    public long countByExample(TbMenusExample example);

    public int deleteByExample(TbMenusExample example);

    public int deleteByPrimaryKey(Long menuId);

    public int insert(TbMenus record);

    public int insertSelective(TbMenus record);

    public List<TbMenus> selectByExample(TbMenusExample example);

    public TbMenus selectByPrimaryKey(Long menuId);

    public int updateByExampleSelective(TbMenus record, TbMenusExample example);

    public int updateByExample(TbMenus record, TbMenusExample example);

    public int updateByPrimaryKeySelective(TbMenus record);

    public int updateByPrimaryKey(TbMenus record);

    public void deleteMenus(Long menuId);
}
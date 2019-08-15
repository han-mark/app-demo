package com.bird.business.service.impl;

import com.bird.business.dao.TbMenusMapper;
import com.bird.business.domain.*;
import com.bird.business.service.ITbMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbMenusServiceImpl implements ITbMenusService {

    @Autowired
    private TbMenusMapper tbMenusMapper;

    @Override
    public long countByExample(TbMenusExample example) {
        return tbMenusMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TbMenusExample example) {
        return tbMenusMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long menuId) {
        return tbMenusMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int insert(TbMenus record) {
        return tbMenusMapper.insert(record);
    }

    @Override
    public int insertSelective(TbMenus record) {
        return tbMenusMapper.insertSelective(record);
    }

    @Override
    public List<TbMenus> selectByExample(TbMenusExample example) {
        return tbMenusMapper.selectByExample(example);
    }

    @Override
    public TbMenus selectByPrimaryKey(Long menuId) {
        return tbMenusMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public int updateByExampleSelective(TbMenus record, TbMenusExample example) {
        return tbMenusMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TbMenus record, TbMenusExample example) {
        return tbMenusMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TbMenus record) {
        return tbMenusMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbMenus record) {
        return tbMenusMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteMenus(Long menuId) {
        //级联删除(只支持三级,使用生成的代码处理,不另行做处理)
        //删除一级
        tbMenusMapper.deleteByPrimaryKey(menuId);
        //删除二级
        TbMenusExample tbMenusExample = new TbMenusExample();
        tbMenusExample.or().andParentIdEqualTo(menuId);
        tbMenusMapper.deleteByExample(tbMenusExample);
        //删除三级
        List<TbMenus> tbMenus = tbMenusMapper.selectByExample(tbMenusExample);
        List<Long> menuIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tbMenus)) {
            for (TbMenus tbMenu : tbMenus) {
                menuIdList.add(tbMenu.getMenuId());
            }
            TbMenusExample tbMenusExample1 = new TbMenusExample();
            tbMenusExample1.or().andParentIdIn(menuIdList);
            tbMenusMapper.deleteByExample(tbMenusExample1);
        }
    }
}
package com.bird.business.service.impl;

import com.bird.business.dao.TbRolesMapper;
import com.bird.business.dao.TbRolesMenusMapper;
import com.bird.business.domain.TbRoles;
import com.bird.business.domain.TbRolesExample;
import com.bird.business.domain.TbRolesMenusExample;
import com.bird.business.domain.TbRolesMenusKey;
import com.bird.business.service.ITbRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbRolesServiceImpl implements ITbRolesService {

    @Autowired
    private TbRolesMapper tbRolesMapper;

    @Autowired
    private TbRolesMenusMapper tbRolesMenusMapper;


    @Override
    public long countByExample(TbRolesExample example) {
        return tbRolesMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TbRolesExample example) {
        return tbRolesMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long roleId) {
        return tbRolesMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(TbRoles record) {
        return tbRolesMapper.insert(record);
    }

    @Override
    public int insertSelective(TbRoles record) {
        return tbRolesMapper.insertSelective(record);
    }

    @Override
    public List<TbRoles> selectByExample(TbRolesExample example) {
        return tbRolesMapper.selectByExample(example);
    }

    @Override
    public TbRoles selectByPrimaryKey(Long roleId) {
        return tbRolesMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByExampleSelective(TbRoles record, TbRolesExample example) {
        return tbRolesMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TbRoles record, TbRolesExample example) {
        return tbRolesMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TbRoles record) {
        return tbRolesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbRoles record) {
        return tbRolesMapper.updateByPrimaryKey(record);
    }

    @Override
    public void addRolesMenus(Long roleId, List<TbRolesMenusKey> list) {
        //先删除
        TbRolesMenusExample tbRolesMenusExample = new TbRolesMenusExample();
        tbRolesMenusExample.or().andRoleIdEqualTo(roleId);
        tbRolesMenusMapper.deleteByExample(tbRolesMenusExample);
        //后新增
        for(TbRolesMenusKey tbRolesMenusKey : list) {
            tbRolesMenusKey.setRoleId(roleId);
            tbRolesMenusMapper.insert(tbRolesMenusKey);
        }
    }
}
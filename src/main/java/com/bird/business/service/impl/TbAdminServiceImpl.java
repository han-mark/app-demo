package com.bird.business.service.impl;

import com.bird.business.dao.TbAdminMapper;
import com.bird.business.domain.TbAdmin;
import com.bird.business.domain.TbAdminExample;
import com.bird.business.service.ITbAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbAdminServiceImpl implements ITbAdminService {

    @Autowired
    private TbAdminMapper tbAdminMapper;

    @Override
    public long countByExample(TbAdminExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TbAdminExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(TbAdmin record) {
        return tbAdminMapper.insert(record);
    }

    @Override
    public int insertSelective(TbAdmin record) {
        return 0;
    }

    @Override
    public List<TbAdmin> selectByExample(TbAdminExample example) {
        return tbAdminMapper.selectByExample(example);
    }

    @Override
    public TbAdmin selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(TbAdmin record, TbAdminExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TbAdmin record, TbAdminExample example) {
        return tbAdminMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TbAdmin record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbAdmin record) {
        return tbAdminMapper.updateByPrimaryKey(record);
    }
}
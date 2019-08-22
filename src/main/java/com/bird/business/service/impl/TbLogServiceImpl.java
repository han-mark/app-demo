package com.bird.business.service.impl;

import com.bird.business.dao.TbLogMapper;
import com.bird.business.domain.TbLog;
import com.bird.business.domain.TbLogExample;
import com.bird.business.service.ITbLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbLogServiceImpl implements ITbLogService {

    @Autowired
    private TbLogMapper tbLogMapper;

    @Override
    public long countByExample(TbLogExample example) {
        return tbLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TbLogExample example) {
        return tbLogMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbLog record) {
        return tbLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TbLog record) {
        return tbLogMapper.insertSelective(record);
    }

    @Override
    public List<TbLog> selectByExample(TbLogExample example) {
        return tbLogMapper.selectByExample(example);
    }

    @Override
    public TbLog selectByPrimaryKey(Long id) {
        return tbLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(TbLog record, TbLogExample example) {
        return tbLogMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TbLog record, TbLogExample example) {
        return tbLogMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TbLog record) {
        return tbLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbLog record) {
        return tbLogMapper.updateByPrimaryKey(record);
    }
}

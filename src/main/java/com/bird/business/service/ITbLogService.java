package com.bird.business.service;

import com.bird.business.domain.TbLog;
import com.bird.business.domain.TbLogExample;
import java.util.List;

public interface ITbLogService {

    public long countByExample(TbLogExample example);

    public int deleteByExample(TbLogExample example);

    public int deleteByPrimaryKey(Long id);

    public int insert(TbLog record);

    public int insertSelective(TbLog record);

    public List<TbLog> selectByExample(TbLogExample example);

    public TbLog selectByPrimaryKey(Long id);

    public int updateByExampleSelective(TbLog record, TbLogExample example);

    public int updateByExample(TbLog record, TbLogExample example);

    public int updateByPrimaryKeySelective(TbLog record);

    public int updateByPrimaryKey(TbLog record);

}
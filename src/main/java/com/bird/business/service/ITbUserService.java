package com.bird.business.service;

import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;

import java.util.List;

public interface ITbUserService {
    public long countByExample(TbUserExample example);

    public int deleteByExample(TbUserExample example);

    public int deleteByPrimaryKey(Long id);

    public int insert(TbUser record);

    public int insertSelective(TbUser record);

    public List<TbUser> selectByExample(TbUserExample example);

    public TbUser selectByPrimaryKey(Long id);

    public int updateByExampleSelective(TbUser record, TbUserExample example);

    public int updateByExample(TbUser record, TbUserExample example);

    public int updateByPrimaryKeySelective(TbUser record);

    public int updateByPrimaryKey(TbUser record);

}
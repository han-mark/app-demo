package com.bird.business.service;

import com.bird.business.domain.TbAdmin;
import com.bird.business.domain.TbAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITbAdminService {
    long countByExample(TbAdminExample example);

    int deleteByExample(TbAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbAdmin record);

    int insertSelective(TbAdmin record);

    List<TbAdmin> selectByExample(TbAdminExample example);

    TbAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByExample(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByPrimaryKeySelective(TbAdmin record);

    int updateByPrimaryKey(TbAdmin record);
}
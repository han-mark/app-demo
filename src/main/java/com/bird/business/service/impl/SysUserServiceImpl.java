package com.bird.business.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bird.business.dao.SysUserDao;
import com.bird.business.domain.SysUser;
import com.bird.business.service.ISysUserService;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    
    @Override
    public List<SysUser> getUserList(Map<String,Object> paramMap) {
        return this.sysUserDao.getUserList(paramMap);
    }

    @Override
    public void deleteUserByUuid(Map<String,Object> paramMap){
        sysUserDao.deleteUserByUuid(paramMap);
    }

    @Override
    public SysUser getUserByUuid(Map<String,Object> paramMap){
        return sysUserDao.getUserByUuid(paramMap);
    }

    @Override
    public void addUser(Map<String, Object> paramMap) {
        sysUserDao.addUser(paramMap);
    }

    @Override
    public void updateUser(Map<String, Object> paramMap) {
        sysUserDao.updateUser(paramMap);
    }
}
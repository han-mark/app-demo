package com.bird.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bird.business.dao.SysUserDao;
import com.bird.business.domain.SysUser;
import com.bird.business.service.ISysUserService;

@Service("iSysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserDao sysUserDao;
    
    @Override
    public SysUser getSysUserById(String username) {
        return this.sysUserDao.findSysUserById(username);
    }

}
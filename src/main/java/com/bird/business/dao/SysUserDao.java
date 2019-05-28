package com.bird.business.dao;

import com.bird.business.domain.SysUser;

public interface SysUserDao {
    public SysUser findSysUserById(String username);
}
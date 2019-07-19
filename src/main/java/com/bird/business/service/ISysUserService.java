package com.bird.business.service;

import com.bird.business.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface ISysUserService {
    public List<SysUser> getUserList(Map<String,Object> paramMap);
    public void deleteUserByUuid(Map<String,Object> paramMap);
    public SysUser getUserByUuid(Map<String,Object> paramMap);
    public void addUser(Map<String,Object> paramMap);
    public void updateUser(Map<String,Object> paramMap);
}
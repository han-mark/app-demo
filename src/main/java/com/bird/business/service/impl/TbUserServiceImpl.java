package com.bird.business.service.impl;

import com.bird.business.dao.TbRolesMapper;
import com.bird.business.dao.TbUserMapper;
import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;
import com.bird.business.service.ITbUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbUserServiceImpl implements ITbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbRolesMapper tbRolesMapper;

    @Override
    public long countByExample(TbUserExample example) {
        return tbUserMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TbUserExample example) {
        return tbUserMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbUser record) {
        //明文密码加密加盐
        String username = record.getUsername();
        String passwd = record.getPassword();
        //MD5加密,用户名作为盐值
        Md5Hash md5Hash = new Md5Hash(passwd, username);
        record.setPassword(md5Hash.toString());
        record.setSalt(username);
        return tbUserMapper.insert(record);
    }

    @Override
    public int insertSelective(TbUser record) {
        return tbUserMapper.insertSelective(record);
    }

    @Override
    public List<TbUser> selectByExample(TbUserExample example) {
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        for (TbUser tbUser : tbUsers) {
            Long roleId = tbUser.getRoleId();
            if (roleId != null){
                tbUser.setRoleName(tbRolesMapper.selectByPrimaryKey(roleId).getRoleName());
            }
        }
        return tbUsers;
    }

    @Override
    public TbUser selectByPrimaryKey(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(TbUser record, TbUserExample example) {
        return tbUserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TbUser record, TbUserExample example) {
        return tbUserMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TbUser record) {
        return tbUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbUser record) {
        return tbUserMapper.updateByPrimaryKey(record);
    }
}

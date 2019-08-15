package com.bird.business.domain;

import java.util.List;

public class TbRoles {
    private Long roleId;

    private String roleName;

    private String roleRemark;

    private List<TbRolesMenusKey> perms;

    public List<TbRolesMenusKey> getPerms() {
        return perms;
    }

    public void setPerms(List<TbRolesMenusKey> perms) {
        this.perms = perms;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }
}
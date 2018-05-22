package com.basic.core.bean.system;

import java.io.Serializable;

public class SystemUserRoleVO implements Serializable{
    //主键id
    private Integer id;

    //用户ID
    private Integer userId;

    //角色ID
    private Integer roleId;

    private String roleName;

    private boolean select;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
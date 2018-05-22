package com.basic.core.bean.system;

import com.basic.core.bean.BaseEntity;

public class SystemRoleResource extends BaseEntity {
    //主键id
    private Integer id;

    //角色ID
    private Integer roleId;

    //资源ID
    private Integer resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
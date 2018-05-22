package com.basic.core.bean.system;


import com.basic.core.bean.BaseEntity;

public class SystemResource extends BaseEntity {
    //主键id
    private Integer id;

    //资源名称
    private String resourceName;

    //资源链接
    private String resourceUrl;

    //资源描述
    private String description;

    //资源图标
    private String resourceIcon;

    //上级ID
    private Integer pid;

    //资源状态(1_有效2_无效)
    private Byte resourceStatus;

    //资源类型(1_一级菜单2_二级菜单3_按钮)
    private Byte resourceType;

    private String pidName;

    private String statusName;

    private String typeName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon == null ? null : resourceIcon.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Byte resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

}
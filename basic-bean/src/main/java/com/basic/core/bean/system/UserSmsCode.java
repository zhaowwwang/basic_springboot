package com.basic.core.bean.system;

import java.io.Serializable;
import java.util.Date;

public class UserSmsCode implements Serializable{
    //ID自增
    private Integer id;

    //手机号
    private String phone;

    //验证码
    private String vrfCode;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getVrfCode() {
        return vrfCode;
    }

    public void setVrfCode(String vrfCode) {
        this.vrfCode = vrfCode == null ? null : vrfCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
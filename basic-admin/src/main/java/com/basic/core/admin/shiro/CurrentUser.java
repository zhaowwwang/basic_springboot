package com.basic.core.admin.shiro;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangzw
 * @Description: 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @Version: 1.0
 * @Date: 2017/4/6 16:36
 */
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public int userId;
    public String realName;
    public String userName;
    public List<String> roleIdList;
    public List<String> roleUrlList;

    public CurrentUser(int userId, String userName, String realName, List<String> roleIdList,List<String> userResourceList) {
        this.userId = userId;
        this.realName = realName;
        this.userName = userName;
        this.roleIdList = roleIdList;
        this.roleUrlList = userResourceList;
    }

    public String getName() {
        return userName;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return realName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<String> getRoleUrlList() {
        return roleUrlList;
    }

    public void setRoleUrlList(List<String> roleUrlList) {
        this.roleUrlList = roleUrlList;
    }
}
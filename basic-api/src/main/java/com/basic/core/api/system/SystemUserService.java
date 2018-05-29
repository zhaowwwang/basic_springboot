package com.basic.core.api.system;

import com.basic.core.bean.system.SystemUser;

import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 9:34
 * @Description: 系统用户接口
 * @Version: 1.0
 */
public interface SystemUserService {

    /**
     * @Author: wangzw
     * @Description: 得到系统用户列表
     * @Version: 1.0
     * @Date: 2017/4/6 10:02
     */
    List<SystemUser> getSystemUserList(SystemUser systemUserEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 用户总数
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    int getSystemUserListCount(SystemUser systemUserEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 根据用户名查询用户详细信息
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    SystemUser getSystemUserByUserName(String userName) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 增加用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    int insertSelective(SystemUser systemUserEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 更新用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    int updateByPrimaryKeySelective(SystemUser systemUserEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 查询用户根据用户ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    SystemUser selectByPrimaryKey(int userId) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 删除用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    int deleteByPrimaryKey(int id) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 查询用户的角色
     * @Version: 1.0
     * @Date: 2017/10/2 14:56
     */
    List<Integer> getUserRoleList(Integer userId);

    int updateUserPwd(SystemUser systemUser);
}

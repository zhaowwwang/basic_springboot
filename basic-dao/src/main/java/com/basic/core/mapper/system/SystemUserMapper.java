package com.basic.core.mapper.system;

import com.basic.core.bean.system.SystemUser;
import java.util.List;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    /**
     * @Author: wangzw
     * @Description: 得到系统用户列表
     * @Version: 1.0
     * @Date: 2017/4/6 10:02
     */
    List<SystemUser> getSystemUserList(SystemUser systemUserEntiy);

    /**
     * @Author: wangzw
     * @Description: 用户总数
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    int getSystemUserListCount(SystemUser systemUserEntiy);

    /**
     * @Author: wangzw
     * @Description: 根据用户名查询用户详细信息
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    SystemUser getSystemUserByUserName(String userName);

    List<Integer> getUserRoleList(Integer userId);

    int updatePwd(SystemUser systemUser);
}
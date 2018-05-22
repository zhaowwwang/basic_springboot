package com.basic.core.mapper.system;

import com.basic.core.bean.system.SystemRole;
import java.util.List;
import java.util.Map;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    List<SystemRole> getSystemRoleList(SystemRole systemRole);

    int getSystemRoleListCount(SystemRole systemRole);

    List<String> getUserRoleList(int userId);

    /**
     * @Author: wangzw
     * @Description: 删除角色对应的资源
     * @Version: 1.0
     * @Date: 2017/10/2 16:21
     */
    int deleteResourceByPrimaryKey(int roleId);

    /**
     * @Author: wangzw
     * @Description: 删除用户对应的角色
     * @Version: 1.0
     * @Date: 2017/10/2 16:21
     */
    int deleteRoleByUserId(int userId);
    /**
     * @Author: wangzw
     * @Description: 批量插入角色对应的资源
     * @Version: 1.0
     * @Date: 2017/10/2 16:14
     */
    int insertSelectiveRoleResource(Map map);

    /**
     * @Author: wangzw
     * @Description: 批量插入用户对应的角色
     * @Version: 1.0
     * @Date: 2017/10/2 16:14
     */
    int insertSelectiveUserRole(Map map);
}
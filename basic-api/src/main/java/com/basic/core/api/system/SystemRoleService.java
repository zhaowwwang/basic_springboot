package com.basic.core.api.system;

import com.basic.core.bean.system.SystemRole;
import com.basic.core.bean.system.vo.SystemUserRoleVo;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 15:11
 * @Description: 系统角色接口
 * @Version: 1.0
 */
public interface SystemRoleService {

    List<SystemRole> getSystemRoleList(SystemRole systemRoleEntiy) throws Exception;

    int getSystemRoleListCount(SystemRole systemRoleEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 得到角色ID相对应的资源ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:59
     */
    List<String> getUserRoleList(int userId) throws Exception;

    int deleteByPrimaryKey(int id) throws Exception;

    int insertSelective(SystemRole systemRoleEntiy) throws Exception;

    int updateByPrimaryKeySelective(SystemRole systemRoleEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 根据角色ID删除角色资源表的资源ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:57
     */
    int deleteResourceByPrimaryKey(int roleId) throws Exception;

    SystemRole selectByPrimaryKey(Integer id) throws Exception;

    List<SystemUserRoleVo> getUserRoleList(SystemRole systemRoleEntiy, Integer userId) throws Exception;

    int insertSelectiveUserRole(Integer userId, List<String> roleIds);
}

package com.basic.core.api.system;

import com.basic.core.bean.system.SystemResource;
import com.basic.core.bean.system.vo.SystemResourceVo;

import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/10 18:40
 * @Description: 系统资源服务
 * @Version: 1.0
 */
public interface SystemResourceService {

    /**
     * @Author: wangzw
     * @Description: 查询全部的菜单
     * @Version: 1.0
     * @Date: 2017/4/13 14:48
     */
    List<SystemResource> getSystemResourceList(SystemResource systemResourceEntiy) throws Exception;

    int getSystemResourceCount(SystemResource systemResourceEntiy) throws Exception;

    /**
     * @Author: wangzw
     * @Description: 查询角色id对应的菜单id
     * @Version: 1.0
     * @Date: 2017/4/13 14:56
     */
    List<String> getUserResourceList(List<String> roleId) throws Exception;

    int deleteByPrimaryKey(int id) throws Exception;

    int insertSelective(SystemResource systemRoleEntiy) throws Exception;

    int updateByPrimaryKeySelective(SystemResource systemRoleEntiy) throws Exception;

    SystemResource selectByPrimaryKey(Integer id) throws Exception;

    List<SystemResource> getOptionList(SystemResource systemResource) throws Exception;

    List<SystemResourceVo> getSystemResourcesAll(int roleId) throws Exception;

    int updateRoleAuth(int roleId, List<String> resourceIds) throws Exception;
}

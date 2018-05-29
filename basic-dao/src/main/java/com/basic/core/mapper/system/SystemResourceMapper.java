package com.basic.core.mapper.system;

import com.basic.core.bean.system.SystemResource;
import java.util.List;

public interface SystemResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemResource record);

    int insertSelective(SystemResource record);

    SystemResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemResource record);

    int updateByPrimaryKey(SystemResource record);

    List<SystemResource> getSystemResourceList(SystemResource systemResource);

    List<SystemResource> getOptionList(SystemResource systemResource);

    int getSystemResourceListCount(SystemResource systemResource);
    /**
     * @Author: wangzw
     * @Description: 查询角色id对应的资源url
     * @Version: 1.0
     * @Date: 2017/4/13 14:56
     */
    List<String> getUserResourceUrlList(List<String> userId);

    /**
     * @Author: wangzw
     * @Description: 查询角色对应的资源ID
     * @Version: 1.0
     * @Date: 2017/10/2 7:16
     */
    List<Integer> getUserResourceIdList(int roleId);

    /**
     * @Author: wangzw
     * @Description: 得到所有的菜单/按钮
     * @Version: 1.0
     * @Date: 2017/10/1 20:51
     */
    List<SystemResource> getAllResourceList(int resourcesType);

    /**
     * @Author: wangzw
     * @Description: 查询所有按钮的pid
     * @Version: 1.0
     * @Date: 2017/10/2 7:16
     */
    List<Integer> getButtonResourcePidList();
}
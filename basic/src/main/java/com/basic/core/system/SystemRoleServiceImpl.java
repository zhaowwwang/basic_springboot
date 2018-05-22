package com.basic.core.system;

import com.basic.core.api.system.SystemRoleService;
import com.basic.core.bean.system.SystemRole;
import com.basic.core.bean.system.SystemUserRoleVO;
import com.basic.core.mapper.system.SystemRoleMapper;
import com.basic.core.mapper.system.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 15:35
 * @Description: 系统角色接口实现
 * @Version: 1.0
 */

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleDao;
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public List<SystemRole> getSystemRoleList(SystemRole systemRoleEntiy) throws Exception {
        return systemRoleDao.getSystemRoleList(systemRoleEntiy);
    }

    @Override
    public int getSystemRoleListCount(SystemRole systemRoleEntiy) throws Exception {
        return systemRoleDao.getSystemRoleListCount(systemRoleEntiy);
    }

    /**
     * @Author: wangzw
     * @Description: 得到角色ID相对应的资源ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:59
     */
    @Override
    public List<String> getUserRoleList(int userId) throws Exception {
        return systemRoleDao.getUserRoleList(userId);
    }

    @Override
    public int deleteByPrimaryKey(int id) throws Exception {
        return systemRoleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SystemRole systemRoleEntiy) throws Exception {
        return systemRoleDao.insertSelective(systemRoleEntiy);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRole systemRoleEntiy) throws Exception {
        return systemRoleDao.updateByPrimaryKeySelective(systemRoleEntiy);
    }

    /**
     * @param roleId
     * @Author: wangzw
     * @Description: 根据角色ID删除角色资源表的资源ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:57
     */
    @Override
    public int deleteResourceByPrimaryKey(int roleId) throws Exception {
        return systemRoleDao.deleteResourceByPrimaryKey(roleId);
    }

    @Override
    public SystemRole selectByPrimaryKey(Integer id) throws Exception {
        return systemRoleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemUserRoleVO> getUserRoleList(SystemRole systemRoleEntiy, Integer userId) throws Exception {
        List<SystemRole> systemRoleList = systemRoleDao.getSystemRoleList(systemRoleEntiy);
        List<Integer> userRoleList = systemUserMapper.getUserRoleList(userId);
        List<SystemUserRoleVO> systemUserRoleVOList = new ArrayList<>();
        SystemRole systemRole = null;
        for (int i = 0; i < systemRoleList.size(); i++) {
            SystemUserRoleVO systemUserRoleVO = new SystemUserRoleVO();
            systemRole = systemRoleList.get(i);
            systemUserRoleVO.setRoleId(systemRole.getId());
            systemUserRoleVO.setRoleName(systemRole.getRoleName());
            systemUserRoleVO.setSelect(userRoleList.contains(systemRole.getId()));
            systemUserRoleVOList.add(systemUserRoleVO);
        }
        return systemUserRoleVOList;
    }

    @Override
    public int insertSelectiveUserRole(Integer userId,List<String> roleIds) {
        systemRoleDao.deleteRoleByUserId(userId);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.putIfAbsent("userId",userId);
        paramMap.putIfAbsent("roleIds",roleIds);
        return systemRoleDao.insertSelectiveUserRole(paramMap);
    }
}

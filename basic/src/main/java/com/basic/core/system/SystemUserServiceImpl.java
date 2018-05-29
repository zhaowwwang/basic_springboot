package com.basic.core.system;

import com.basic.core.api.system.SystemUserService;
import com.basic.core.bean.system.SystemUser;
import com.basic.core.mapper.system.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 9:34
 * @Description: 系统用户服务接口实现
 * @Version: 1.0
 */

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserDao;

    /**
     * @param systemUserEntiy
     * @Author: wangzw
     * @Description: 得到系统用户列表
     * @Version: 1.0
     * @Date: 2017/4/6 10:02
     */
    @Override
    public List<SystemUser> getSystemUserList(SystemUser systemUserEntiy) throws Exception {
        return systemUserDao.getSystemUserList(systemUserEntiy);
    }

    @Override
    public int getSystemUserListCount(SystemUser systemUserEntiy) throws Exception {
        return systemUserDao.getSystemUserListCount(systemUserEntiy);
    }

    @Override
    public SystemUser getSystemUserByUserName(String userName) throws Exception {
        return systemUserDao.getSystemUserByUserName(userName);
    }

    /**
     * @param systemUserEntiy
     * @Author: wangzw
     * @Description: 增加用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:14
     */
    @Override
    public int insertSelective(SystemUser systemUserEntiy) throws Exception {
        return systemUserDao.insertSelective(systemUserEntiy);
    }

    /**
     * @param systemUserEntiy
     * @Author: wangzw
     * @Description: 更新用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    @Override
    public int updateByPrimaryKeySelective(SystemUser systemUserEntiy) throws Exception {
        return systemUserDao.updateByPrimaryKeySelective(systemUserEntiy);
    }

    /**
     * @param userId
     * @Author: wangzw
     * @Description: 查询用户根据用户ID
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    @Override
    public SystemUser selectByPrimaryKey(int userId) throws Exception {
        return systemUserDao.selectByPrimaryKey(userId);
    }

    /**
     * @param id
     * @Author: wangzw
     * @Description: 删除用户
     * @Version: 1.0
     * @Date: 2017/4/12 20:15
     */
    @Override
    public int deleteByPrimaryKey(int id) throws Exception {
        return systemUserDao.deleteByPrimaryKey(id);
    }

    /**
     * @param userId
     * @Author: wangzw
     * @Description: 查询用户的角色
     * @Version: 1.0
     * @Date: 2017/10/2 14:56
     */
    @Override
    public List<Integer> getUserRoleList(Integer userId) {
        return systemUserDao.getUserRoleList(userId);
    }

    @Override
    public int updateUserPwd(SystemUser systemUser) {
        return systemUserDao.updateUserPwd(systemUser);
    }
}

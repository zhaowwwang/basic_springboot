package com.basic.core.system;

import com.basic.core.api.system.SystemOrganizationService;
import com.basic.core.bean.system.SystemOrganization;
import com.basic.core.mapper.system.SystemOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 20:30
 * @Description: 系统部门服务接口实现
 * @Version: 1.0
 */
@Service
public class SystemOrganizationServiceImpl implements SystemOrganizationService {

    @Autowired
    private SystemOrganizationMapper systemOrganizationDao;

    @Override
    public List<SystemOrganization> getSystemOrgList(SystemOrganization systemOrganizationEntiy) throws Exception {
        return systemOrganizationDao.getSystemOrgList(systemOrganizationEntiy);
    }

    @Override
    public int getSystemOrgListCount(SystemOrganization systemOrganizationEntiy) throws Exception {
        return systemOrganizationDao.getSystemOrgListCount(systemOrganizationEntiy);
    }

    @Override
    public int insertSelective(SystemOrganization record) throws Exception {
        return systemOrganizationDao.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return systemOrganizationDao.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemOrganization record) throws Exception {
        return systemOrganizationDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public SystemOrganization selectByPrimaryKey(Integer id) throws Exception {
        return systemOrganizationDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemOrganization> getOrgOption() throws Exception {
        return systemOrganizationDao.getOrgOption();
    }
}

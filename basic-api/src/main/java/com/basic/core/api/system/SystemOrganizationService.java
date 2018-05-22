package com.basic.core.api.system;


import com.basic.core.bean.system.SystemOrganization;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/6 20:30
 * @Description:
 * @Version: 1.0
 */
public interface SystemOrganizationService {

    List<SystemOrganization> getSystemOrgList(SystemOrganization systemOrganizationEntiy) throws Exception;

    int getSystemOrgListCount(SystemOrganization systemOrganizationEntiy) throws Exception;

    int insertSelective(SystemOrganization record) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SystemOrganization record) throws Exception;

    SystemOrganization selectByPrimaryKey(Integer id) throws Exception;

    List<SystemOrganization> getOrgOption() throws Exception;
}

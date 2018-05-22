package com.basic.core.mapper.system;

import com.basic.core.bean.system.SystemOrganization;

import java.util.List;

public interface SystemOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemOrganization record);

    int insertSelective(SystemOrganization record);

    SystemOrganization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemOrganization record);

    int updateByPrimaryKey(SystemOrganization record);

    List<SystemOrganization> getSystemOrgList(SystemOrganization systemOrganization);

    int getSystemOrgListCount(SystemOrganization systemOrganization);

    List<SystemOrganization> getOrgOption();
}
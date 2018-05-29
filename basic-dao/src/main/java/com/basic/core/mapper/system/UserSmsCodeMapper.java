package com.basic.core.mapper.system;

import com.basic.core.bean.system.UserSmsCode;

public interface UserSmsCodeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserSmsCode record);

    int insertSelective(UserSmsCode record);

    UserSmsCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSmsCode record);

    int updateByPrimaryKey(UserSmsCode record);

    UserSmsCode getFiveCodeByMobile(UserSmsCode userSmsCode);

    int selectCountByPhone(String phone);
}
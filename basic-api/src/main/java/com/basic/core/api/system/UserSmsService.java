package com.basic.core.api.system;


import com.basic.core.bean.system.UserSmsCode;

/**
 * @author: wangzw
 * @date: 2017/12/26 14:26
 * @version: 1.0
 */
public interface UserSmsService {

    UserSmsCode getFiveCodeByMobile(UserSmsCode userSmsCode);

    int selectCountByPhone(String phone);

    int insertSelective(UserSmsCode record);

}

package com.basic.core.system;

import com.basic.core.api.system.UserSmsService;
import com.basic.core.bean.system.UserSmsCode;
import com.basic.core.mapper.system.UserSmsCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信验证码
 * @author: wangzw
 * @date: 2017/12/26 14:27
 * @version: 1.0
 */
@Service
public class UserSmsServiceImpl implements UserSmsService {

    @Autowired
    private UserSmsCodeMapper userSmsCodeMapper;

    @Override
    public UserSmsCode getFiveCodeByMobile(UserSmsCode userSmsCode) {
        return userSmsCodeMapper.getFiveCodeByMobile(userSmsCode);
    }

    @Override
    public int selectCountByPhone(String phone) {
        return userSmsCodeMapper.selectCountByPhone(phone);
    }

    @Override
    public int insertSelective(UserSmsCode record) {
        return userSmsCodeMapper.insertSelective(record);
    }
}

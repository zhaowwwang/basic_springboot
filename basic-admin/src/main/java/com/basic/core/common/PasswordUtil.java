package com.basic.core.common;

import com.basic.core.bean.system.SystemUser;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 用户密码工具类
 * @Author: wangzw
 * @Date: 2017/10/2 16:45
 * @Description:
 * @Version: 1.0
 */
public class PasswordUtil {

    public static void setUserPwd(SystemUser systemUser) throws Exception{
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        systemUser.setUserSalt(salt);
        systemUser.setPassword(new SimpleHash("MD5",systemUser.getUserName()+systemUser.getPassword(),salt,2).toHex());
    }

    public static boolean checkUserPwd(SystemUser systemUser,String currentName,String currentPwd){
        String salt = systemUser.getUserSalt();
        String toHex = new SimpleHash("MD5", currentName + currentPwd, salt, 2).toHex();
        if(toHex.equals(systemUser.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    public static void main(String[] args) {
        String uesrName="admin";
        String userPwd="admin";
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("salt==="+salt);
        String toHex = new SimpleHash("MD5", uesrName + userPwd, salt, 2).toHex();
        System.out.println("pwd===="+toHex);
    }
}

package com.basic.core.admin.shiro;

import com.basic.core.admin.common.PasswordUtil;
import com.basic.core.api.system.SystemResourceService;
import com.basic.core.api.system.SystemRoleService;
import com.basic.core.api.system.SystemUserService;
import com.basic.core.bean.system.SystemUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/4/1 11:49
 * @Description:
 * @Version: 1.0
 */
public class ShiroRealm extends AuthorizingRealm{

    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * @Author: wangzw
     * @Description: Shiro登录认证(原理 用户提交 用户名和密码 shiro 封装令牌 realm 通过用户名将密码查询返回 shiro 自动去比较查询出密码和用户输入密码是否一致,进行登陆控制 )
     * @Version: 1.0
     * @Date: 2017/4/6 16:27
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("Shiro开始登录认证");
        //当前登录的用户名
        String currUserName = (String) authcToken.getPrincipal();
        //当前登录的密码
        String currPassWord = new String((char[]) authcToken.getCredentials());
        SystemUser systemUserEntiy = null;
        try {
            systemUserEntiy = systemUserService.getSystemUserByUserName(currUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(systemUserEntiy ==null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if(systemUserEntiy.getUserStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定，请联系管理员！");
        }
        if(PasswordUtil.checkUserPwd(systemUserEntiy,currUserName,currPassWord) == false){
            throw new IncorrectCredentialsException("账号或密码不正确！");
        }
        List<String> roleList = null;
        List<String> userResourceList = null;
        try {
            roleList = systemRoleService.getUserRoleList(systemUserEntiy.getId());
            userResourceList = systemResourceService.getUserResourceList(roleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CurrentUser currentUser = new CurrentUser(systemUserEntiy.getId(), systemUserEntiy.getUserName(),systemUserEntiy.getRealName(),roleList,userResourceList);
        return new SimpleAuthenticationInfo(currentUser,currPassWord,getName());
    }

    /**
     * @Author: wangzw
     * @Description: Shiro权限认证
     * @Version: 1.0
     * @Date: 2017/4/6 16:28
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CurrentUser currentUser = (CurrentUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(currentUser.getRoleUrlList());
        return info;
    }

}

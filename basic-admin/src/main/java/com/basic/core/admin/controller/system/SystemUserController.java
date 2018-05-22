package com.basic.core.admin.controller.system;

import com.basic.core.api.system.SystemRoleService;
import com.basic.core.api.system.SystemUserService;
import com.basic.core.bean.system.SystemRole;
import com.basic.core.bean.system.SystemUser;
import com.basic.core.bean.system.SystemUserRoleVO;
import com.basic.core.admin.common.PasswordUtil;
import com.basic.core.admin.controller.BaseController;
import com.basic.core.util.WebPageUtils;
import com.basic.core.web.WebJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangzw
 * @Description: 系统用户
 * @Version: 1.0
 * @Date: 2017/3/21 16:41
 */
@Controller
@RequestMapping(value="/systemUser")
@ResponseBody
public class SystemUserController extends BaseController {

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleService systemRoleService;

	/**
	 * @Author: wangzw
	 * @Description: 系统用户列表
	 * @Version: 1.0
	 * @Date: 2017/4/6 11:24
	 */
	@RequestMapping(value="/getListData")
	public WebJson getListData(SystemUser systemUserEntiy){
		try{
			List<SystemUser> systemUserEntiyList = systemUserService.getSystemUserList(systemUserEntiy);
			int listCount = systemUserService.getSystemUserListCount(systemUserEntiy);
			WebPageUtils webPageUtils = getPageUtils(systemUserEntiyList,listCount,systemUserEntiy.getPageSize(),systemUserEntiy.getPageNo());
			return returnDataToWeb(webPageUtils);
		}catch(Exception e){
		    e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	@RequestMapping("/addUser")
	public WebJson addUser(SystemUser systemUserEntiy){
		try{
			systemUserEntiy.setUserStatus("1");
			PasswordUtil.setUserPwd(systemUserEntiy);
			systemUserService.insertSelective(systemUserEntiy);
			return SuccessToWeb("增加用户成功！");
		}catch(Exception e){
		    e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	@RequestMapping("/updateUser")
	public WebJson updateUser(SystemUser systemUserEntiy){
		try{
			systemUserService.updateByPrimaryKeySelective(systemUserEntiy);
			return SuccessToWeb("修改用户信息成功！");
		}catch(Exception e){
		    e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	@RequestMapping("/delUser")
	public WebJson delUser(int id){
		try{
			systemUserService.deleteByPrimaryKey(id);
			return SuccessToWeb("删除用户成功！");
		}catch(Exception e){
			e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	@RequestMapping("/viewUser")
	public WebJson viewUser(int id){
		try{
			SystemUser systemUser = systemUserService.selectByPrimaryKey(id);
			return returnDataToWeb(systemUser);
		}catch(Exception e){
			e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	@RequestMapping("/updatePwd")
	public WebJson updatePwd(SystemUser systemUserEntiy){
		try{
			PasswordUtil.setUserPwd(systemUserEntiy);
			systemUserService.updatePwd(systemUserEntiy);
			return SuccessToWeb("强制改密成功！");
		}catch(Exception e){
			e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	/**
	 * @Author: wangzw
	 * @Description: 系统角色列表
	 * @Version: 1.0
	 * @Date: 2017/4/6 11:24
	 */
	@RequestMapping(value="/getRoleList")
	public WebJson getRoleList(Integer userId){
		try{
			SystemRole systemRoleEntiy = new SystemRole();
			List<SystemUserRoleVO> userRoleList = systemRoleService.getUserRoleList(systemRoleEntiy, userId);
			return returnDataToWeb(userRoleList);
		}catch(Exception e){
			e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

	/**
	 * @Author: wangzw
	 * @Description: 更新系统用户角色
	 * @Version: 1.0
	 * @Date: 2017/4/6 11:24
	 */
	@RequestMapping(value="/updateUserRole")
	public WebJson updateUserRole(Integer userId,@RequestParam(value = "roleIds[]") List<String> roleIds){
		try{
			systemRoleService.insertSelectiveUserRole(userId,roleIds);
			return SuccessToWeb("用户更新角色成功！");
		}catch(Exception e){
			e.printStackTrace();
			return FailToWeb("系统内部异常，请稍后再试！");
		}
	}

}

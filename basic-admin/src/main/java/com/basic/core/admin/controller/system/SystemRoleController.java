package com.basic.core.admin.controller.system;

import com.basic.core.api.system.SystemResourceService;
import com.basic.core.api.system.SystemRoleService;
import com.basic.core.bean.system.vo.SystemResourceVo;
import com.basic.core.bean.system.SystemRole;
import com.basic.core.admin.controller.BaseController;
import com.basic.core.util.WebPageUtils;
import com.basic.core.web.WebJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/9/15 14:47
 * @Description: 系统角色
 * @Version: 1.0
 */

@Controller
@RequestMapping(value="/systemRole")
@ResponseBody
public class SystemRoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * @Author: wangzw
     * @Description: 系统角色列表
     * @Version: 1.0
     * @Date: 2017/4/6 11:24
     */
    @RequestMapping(value="/getListData.do")
    public WebJson getRoleList(SystemRole systemRoleEntiy){
        try{
            List<SystemRole> systemRoleList = systemRoleService.getSystemRoleList(systemRoleEntiy);
            int listCount = systemRoleService.getSystemRoleListCount(systemRoleEntiy);
            WebPageUtils webPageUtils = getPageUtils(systemRoleList, listCount, systemRoleEntiy.getPageSize(), systemRoleEntiy.getPageNo());
            return returnDataToWeb(webPageUtils);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("系统内部异常，请稍后再试！");
        }
    }

    @RequestMapping("addRole.do")
    public WebJson addSystemRole(SystemRole systemRole){
        try{
            systemRoleService.insertSelective(systemRole);
            return SuccessToWeb("增加角色成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("增加角色失败，请稍后再试！");
        }
    }

    @RequestMapping("editRole.do")
    public WebJson editSystemRole(SystemRole systemRole){
        try{
            if(StringUtils.isEmpty(systemRole.getId())){
                return FailToWeb("角色ID为空，无法完成更新操作！");
            }
            systemRoleService.updateByPrimaryKeySelective(systemRole);
            return SuccessToWeb("更新角色信息成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("增加角色信息失败，请稍后再试！");
        }
    }

    @RequestMapping("delRole.do")
    public WebJson deleteSystemRole(SystemRole systemRole){
        try{
            if(StringUtils.isEmpty(systemRole.getId())){
                return FailToWeb("角色ID为空，无法完成更新操作！");
            }
            systemRoleService.deleteByPrimaryKey(systemRole.getId());
            systemRoleService.deleteResourceByPrimaryKey(systemRole.getId());
            return SuccessToWeb("删除角色信息成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("删除角色信息失败，请稍后再试！");
        }
    }

    @RequestMapping("viewRole.do")
    public WebJson viewSystemRole(SystemRole systemRole){
        try{
            if(StringUtils.isEmpty(systemRole.getId())){
                return FailToWeb("角色ID为空，无法完成查看操作！");
            }
            return returnDataToWeb(systemRoleService.selectByPrimaryKey(systemRole.getId()));
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("删除角色信息失败，请稍后再试！");
        }
    }

    /**
     * @Author: wangzw
     * @Description: 得到系统全部的菜单和角色相对应的菜单
     * @Version: 1.0
     * @Date: 2017/10/1 21:48
     */
    @RequestMapping("viewRoleAuth.do")
    public WebJson viewUserAuth(SystemRole systemRole){
        try{
            List<SystemResourceVo> systemResourcesAll = systemResourceService.getSystemResourcesAll(systemRole.getId());
            return returnDataToWeb(systemResourcesAll);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("删除角色信息失败，请稍后再试！");
        }
    }

    /**
     * @Author: wangzw
     * @Description: 更新角色对应的资源
     * @Version: 1.0
     * @Date: 2017/10/2 14:47
     */
    @RequestMapping("updateRoleAuth.do")
    public WebJson updateRoleAuth(int roleId,@RequestParam(value = "ids[]") List<String> ids){
        try{
            systemResourceService.updateRoleAuth(roleId,ids);
            return SuccessToWeb("更新授权信息成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("更新授权信息失败，请稍后再试！");
        }
    }
}

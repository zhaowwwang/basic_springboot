package com.basic.core.controller.system;

import com.basic.core.api.system.SystemOrganizationService;
import com.basic.core.bean.system.SystemOrganization;
import com.basic.core.controller.BaseController;
import com.basic.core.util.WebPageUtils;
import com.basic.core.web.WebJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/9/28 20:36
 * @Description: 机构管理
 * @Version: 1.0
 */
@Controller
@RequestMapping("/systemOrg")
@ResponseBody
public class SystemOrganizationController extends BaseController {

    @Autowired
    private SystemOrganizationService systemOrganizationService;

    /**
     * @Author: wangzw
     * @Description: 系统机构列表
     * @Version: 1.0
     * @Date: 2017/4/6 11:24
     */
    @RequestMapping(value="/getOrgList")
    public WebJson getOrgList(SystemOrganization systemOrganization){
        try{
            List<SystemOrganization> systemOrgList = systemOrganizationService.getSystemOrgList(systemOrganization);
            int orgListCount = systemOrganizationService.getSystemOrgListCount(systemOrganization);
            WebPageUtils webPageUtils = getPageUtils(systemOrgList, orgListCount, systemOrganization.getPageSize(), systemOrganization.getPageNo());
            return returnDataToWeb(webPageUtils);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("系统内部异常，请稍后再试！");
        }
    }

    @RequestMapping("/addOrg")
    public WebJson addSystemOrg(SystemOrganization systemOrganization){
        try{
            systemOrganizationService.insertSelective(systemOrganization);
            return SuccessToWeb("增加机构成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("增加机构异常，请稍后再试！");
        }
    }

    @RequestMapping("/editOrg")
    public WebJson editSystemOrg(SystemOrganization systemOrganization){
        try{
            if(StringUtils.isEmpty(systemOrganization.getId())){
                return FailToWeb("机构ID为空，无法完成更新操作！");
            }
            systemOrganizationService.updateByPrimaryKeySelective(systemOrganization);
            return SuccessToWeb("更新机构信息成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("更新机构信息异常，请稍后再试！");
        }
    }

    @RequestMapping("/delOrg")
    public WebJson deleteSystemOrg(SystemOrganization systemOrganization){
        try{
            if(StringUtils.isEmpty(systemOrganization.getId())){
                return FailToWeb("机构ID为空，无法完成删除操作！");
            }
            systemOrganizationService.deleteByPrimaryKey(systemOrganization.getId());
            return SuccessToWeb("删除机构信息成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("删除机构信息异常，请稍后再试！");
        }
    }

    @RequestMapping("/viewOrg")
    public WebJson viewSystemOrg(SystemOrganization systemOrganization){
        try{
            if(StringUtils.isEmpty(systemOrganization.getId())){
                return FailToWeb("机构ID为空，无法完成查看操作！");
            }
            return returnDataToWeb(systemOrganizationService.selectByPrimaryKey(systemOrganization.getId()));
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("查看机构信息异常，请稍后再试！");
        }
    }

    @RequestMapping("getOrgOption")
    public WebJson getOrgOption(){
        try{
            List<SystemOrganization> systemOrganizations = systemOrganizationService.getOrgOption();
            return returnDataToWeb(systemOrganizations);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("查询机构信息异常，请稍后再试！");
        }
    }
}

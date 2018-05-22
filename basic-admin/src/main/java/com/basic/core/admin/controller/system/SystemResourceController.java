package com.basic.core.admin.controller.system;

import com.basic.core.api.system.SystemResourceService;
import com.basic.core.bean.system.SystemResource;
import com.basic.core.admin.controller.BaseController;
import com.basic.core.util.WebPageUtils;
import com.basic.core.web.WebJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzw
 * @Date: 2017/9/15 14:47
 * @Description: 系统资源
 * @Version: 1.0
 */

@Controller
@RequestMapping(value="/systemResource")
@ResponseBody
public class SystemResourceController extends BaseController {

    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * @Author: wangzw
     * @Description: 系统资源列表
     * @Version: 1.0
     * @Date: 2017/4/6 11:24
     */
    @RequestMapping(value="/getResourceList")
    public WebJson getRoleList(SystemResource systemResource){
        try{
            List<SystemResource> systemResourceList = systemResourceService.getSystemResourceList(systemResource);
            int listCount = systemResourceService.getSystemResourceCount(systemResource);
            WebPageUtils webPageUtils = getPageUtils(systemResourceList, listCount, systemResource.getPageSize(), systemResource.getPageNo());
            return returnDataToWeb(webPageUtils);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("系统内部异常，请稍后再试！");
        }
    }

    @RequestMapping("addResource")
    public WebJson addSystemResource(SystemResource systemResource){
        try{
            systemResourceService.insertSelective(systemResource);
            return SuccessToWeb("添加系统资源成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("添加资源异常，请稍后再试！");
        }
    }

    @RequestMapping("editResource")
    public WebJson editSystemResource(SystemResource systemResource){
        try{
            systemResourceService.updateByPrimaryKeySelective(systemResource);
            return SuccessToWeb("更新资源成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("更新资源异常，请稍后再试！");
        }
    }

    @RequestMapping("delResource")
    public WebJson deleteSystemResource(SystemResource systemResource){
        try{
            systemResourceService.deleteByPrimaryKey(systemResource.getId());
            return SuccessToWeb("删除资源成功！");
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("删除资源异常，请稍后再试！");
        }
    }

    @RequestMapping("viewResource")
    public WebJson viewSystemResource(SystemResource systemResource){
        try{
            return returnDataToWeb(systemResourceService.selectByPrimaryKey(systemResource.getId()));
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("查询资源异常，请稍后再试！");
        }
    }

    @RequestMapping("getResourceOption")
    public WebJson getResourceOption(){
        try{
            SystemResource systemResource = new SystemResource();
            List<SystemResource> optionList = systemResourceService.getOptionList(systemResource);
            Map<String,String> statusMap = new HashMap<>();
            statusMap.putIfAbsent("1","有效");
            statusMap.putIfAbsent("2","无效");
            Map<String,String> typeMap = new HashMap<>();
            typeMap.putIfAbsent("1","一级菜单");
            typeMap.putIfAbsent("2","二级菜单");
            typeMap.putIfAbsent("3","按钮");
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.putIfAbsent("pidOption",optionList);
            resultMap.putIfAbsent("statusOption",statusMap);
            resultMap.putIfAbsent("typeOption",typeMap);
            return returnDataToWeb(resultMap);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("组装资源下拉框内容异常，请稍后再试！");
        }
    }

    @RequestMapping("getPidResource")
    public WebJson getPidResource(){
        try{
            SystemResource systemResource = new SystemResource();
            systemResource.setResourceType(new Byte("1"));
            List<SystemResource> optionList = systemResourceService.getOptionList(systemResource);
            return returnDataToWeb(optionList);
        }catch(Exception e){
            e.printStackTrace();
            return FailToWeb("组装资源下拉框内容异常，请稍后再试！");
        }
    }

}

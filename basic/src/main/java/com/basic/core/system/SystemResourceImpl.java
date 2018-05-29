package com.basic.core.system;

import com.basic.core.api.system.SystemResourceService;
import com.basic.core.bean.system.SystemResource;
import com.basic.core.bean.system.vo.SystemResourceVo;
import com.basic.core.mapper.system.SystemResourceMapper;
import com.basic.core.mapper.system.SystemRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzw
 * @Date: 2017/4/10 18:41
 * @Description:
 * @Version: 1.0
 */
@Service
public class SystemResourceImpl implements SystemResourceService {

    @Autowired
    private SystemResourceMapper systemResourceDao;
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Override
    public List<SystemResource> getSystemResourceList(SystemResource systemResourceEntiy) throws Exception {
        return systemResourceDao.getSystemResourceList(systemResourceEntiy);
    }

    @Override
    public int getSystemResourceCount(SystemResource systemResourceEntiy) throws Exception {
        return systemResourceDao.getSystemResourceListCount(systemResourceEntiy);
    }

    @Override
    public List<String> getUserResourceList(List<String> roleId) throws Exception {
        return systemResourceDao.getUserResourceUrlList(roleId);
    }

    @Override
    public int deleteByPrimaryKey(int id) throws Exception {
        return systemResourceDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SystemResource systemRoleEntiy) throws Exception {
        return systemResourceDao.insertSelective(systemRoleEntiy);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemResource systemRoleEntiy) throws Exception {
        return systemResourceDao.updateByPrimaryKeySelective(systemRoleEntiy);
    }

    @Override
    public SystemResource selectByPrimaryKey(Integer id) throws Exception {
        return systemResourceDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemResource> getOptionList(SystemResource systemResource) throws Exception {
        return systemResourceDao.getOptionList(systemResource);
    }

    /**
     * @Author: wangzw
     * @Description: 得到所有的菜单并根据角色ID标识权限
     * @Version: 1.0
     * @Date: 2017/10/1 20:03
     */
    @Override
    public List<SystemResourceVo> getSystemResourcesAll(int roleId) throws Exception {
        try{
            List<SystemResource> baseResourceList = systemResourceDao.getAllResourceList(1);
            if(baseResourceList.size() > 0){
                List<SystemResource> secondResourceList = systemResourceDao.getAllResourceList(2);
                List<SystemResource> buttonResourceList = null;
                List<Integer> roleResourceIdList = null;
                List<Integer> buttonResourcePidList = null;
                if(secondResourceList.size() > 0){
                    //所有的按钮
                    buttonResourceList = systemResourceDao.getAllResourceList(3);
                    //角色对应的资源ID
                    roleResourceIdList = systemResourceDao.getUserResourceIdList(roleId);
                    //所有按钮的PID
                    buttonResourcePidList = systemResourceDao.getButtonResourcePidList();
                }
                return buildSystemResourceVOList(baseResourceList,secondResourceList,buttonResourceList,roleResourceIdList,buttonResourcePidList);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateRoleAuth(int roleId,List<String> resourceIds) throws Exception {
        systemRoleMapper.deleteResourceByPrimaryKey(roleId);
        Map<String,Object> paramMap = new HashMap<>(2);
        paramMap.putIfAbsent("roleId",roleId);
        paramMap.putIfAbsent("resourceIds",resourceIds);
        systemRoleMapper.insertSelectiveRoleResource(paramMap);
        return 0;
    }

    /**
     * @Author: wangzw
     * @Description: 构建SystemResourceVO
     * @Param: baseResourceList 一级菜单
     * @Param: secondResourceList 二级菜单
     * @Param: secondResourceList 按钮
     * @Param: roleResourceIdList 角色对应资源ID
     * @Param: buttonResourcePidList 所有按钮的PID
     * @Version: 1.0
     * @Date: 2017/10/2 7:32
     */
    private List<SystemResourceVo> buildSystemResourceVOList(List<SystemResource> baseResourceList,
                                                             List<SystemResource> secondResourceList, List<SystemResource> buttonResourceList,
                                                             List<Integer> roleResourceIdList, List<Integer> buttonResourcePidList) throws Exception{
        List<SystemResourceVo> systemResourceVoList = new ArrayList<>();
        try{
            SystemResourceVo systemResourceVo = null;
            SystemResource baseSystemResource = null ,secondSystemResource = null,buttonSystemResource = null;
            for (int i = 0; i < baseResourceList.size(); i++) {
                systemResourceVo = new SystemResourceVo();
                baseSystemResource = baseResourceList.get(i);
                systemResourceVo.setId(baseSystemResource.getId());
                systemResourceVo.setResourceName(baseSystemResource.getResourceName());
                systemResourceVo.setSelect(roleResourceIdList.contains(baseSystemResource.getId()));
                List<SystemResourceVo.SecondResource> secondResourceListT = new ArrayList<>();
                for (int j = 0; j < secondResourceList.size(); j++) {
                    secondSystemResource = secondResourceList.get(j);
                    if(secondSystemResource.getPid() > baseSystemResource.getId() || baseSystemResource.getId().equals(secondSystemResource.getPid()) == false){
                        continue;
                    }
                    SystemResourceVo.SecondResource secondResource = systemResourceVo.new SecondResource();
                    secondResource.setId(secondSystemResource.getId());
                    secondResource.setResourceName(secondSystemResource.getResourceName());
                    secondResource.setSelect(roleResourceIdList.contains(secondSystemResource.getId()));
                    int indexOf = buttonResourcePidList.indexOf(secondSystemResource.getId());
                    List<SystemResourceVo.ButtonResource> buttonResourceListT = new ArrayList<>();
                    for (int k = indexOf;indexOf >= 0 && k < buttonResourceList.size(); k++) {
                        buttonSystemResource = buttonResourceList.get(k);
                        if(buttonSystemResource.getPid() > secondSystemResource.getId()){
                            break;
                        }
                        SystemResourceVo.ButtonResource buttonResource = systemResourceVo.new ButtonResource();
                        buttonResource.setId(buttonSystemResource.getId());
                        buttonResource.setResourceName(buttonSystemResource.getResourceName());
                        buttonResource.setSelect(roleResourceIdList.contains(buttonSystemResource.getId()));
                        buttonResourceListT.add(buttonResource);
                    }
                    secondResource.setButtonResourceList(buttonResourceListT);
                    secondResourceListT.add(secondResource);
                }
                systemResourceVo.setSecondResourceList(secondResourceListT);
                systemResourceVoList.add(systemResourceVo);
            }
            return systemResourceVoList;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
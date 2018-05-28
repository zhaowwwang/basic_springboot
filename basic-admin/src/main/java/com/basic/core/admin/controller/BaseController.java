package com.basic.core.admin.controller;


import com.basic.core.admin.shiro.CurrentUser;
import com.basic.core.admin.shiro.ShiroUtils;
import com.basic.core.util.WebPageUtils;
import com.basic.core.web.WebJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/3/31 15:56
 * @Description: 抽象controller
 * @Version: 1.0
 */
@Controller
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    protected CurrentUser getCurrentUser(){
        return ShiroUtils.getCurrentUser();
    }

    protected ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    protected WebPageUtils getPageUtils(List<?> dataList, int totalCount, int pageSize, int currPage){
        return new WebPageUtils(dataList,totalCount,pageSize,currPage);
    }

    protected WebJson returnDataToWeb(Object val){
        return WebJson.success(val);
    }

    protected WebJson SuccessToWeb(String msg){
        return WebJson.success(msg);
    }

    protected WebJson FailToWeb(String errorMsg){
        return WebJson.error(errorMsg);
    }

    @RequestMapping(value = "/goToHtml.do",method = RequestMethod.GET)
    public String toListHtml(@RequestParam String goToHtmlUrl){
        try{
            return goToHtmlUrl;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "error";
    }
}

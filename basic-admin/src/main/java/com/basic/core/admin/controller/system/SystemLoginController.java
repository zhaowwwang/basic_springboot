package com.basic.core.admin.controller.system;

import com.basic.core.admin.constant.SystemConstant;
import com.basic.core.admin.controller.BaseController;
import com.basic.core.admin.shiro.ShiroUtils;
import com.basic.core.web.WebJson;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangzw
 * @Date: 2017/3/31 15:51
 * @Description:
 * @Version: 1.0
 */
@Controller
public class SystemLoginController extends BaseController {

    /**
     * 首页
     */
    @RequestMapping(value = "/index")
    public String login() {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "Spring Boot");
        return "index";
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/systemIndex")
    public String index() {
        if(ShiroUtils.isLogin()){
            return "main";
        }
        return "redirect: /login_toLogin";
    }

    /**
     * @Author: wangzw
     * @Description: 未授权或首页请求
     * @Version: 1.0
     * @Date: 2017/4/1 14:03
     */
    @RequestMapping("/login_toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv = getModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @RequestMapping("/login_confirmLogin")
    @ResponseBody
    public Object login_confirmLogin(String loginData){
        if(loginData==null){
            return WebJson.error(99,"请求登录信息异常，请修改后再试！");
        }
        String[] loginDataS = loginData.split(",wzw,");
        if(loginDataS.length!=3){
            return WebJson.error(99,"请求登录信息异常，请修改后再试！");
        }
        String userName = loginDataS[0];
        String passWord = loginDataS[1];
        String verifyCode = loginDataS[2];
        if(verifyCode.toUpperCase().equals(ShiroUtils.getKaptcha(SystemConstant.SYS_CURRENT_LOGINUSER_VERIFYCODE).toUpperCase())==false){
            return WebJson.error(98,"验证码不正确！");
        }
        Subject currUser = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        token.setRememberMe(true);
        try {
            currUser.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e);
            return WebJson.error(99,"账号不存在");
        }catch (IncorrectCredentialsException e) {
            logger.error("密码错误：{}", e);
            return WebJson.error(97,"密码错误");
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e);
            return WebJson.error(99,"未知错误,请联系管理员");
        }
        ShiroUtils.getSession().removeAttribute(SystemConstant.SYS_CURRENT_LOGINUSER_VERIFYCODE);
        return SuccessToWeb("success");
    }

    @RequestMapping("/login_toLogOut")
    @ResponseBody
    public WebJson logout(){
        ShiroUtils.logout();
        return SuccessToWeb("退出成功！");
    }

}

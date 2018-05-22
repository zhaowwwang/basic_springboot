<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>

<head>
    <title>BASIC-PLATFORM</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/common.css">
    <link rel="stylesheet" href="/static/css/topLeft.css">
    <link rel="stylesheet" href="/static/css/project.css">
    <link rel="shortcut icon" href="/static/images/logo.png" type="image/x-icon" />
</head>

<body>
<div class="mengban"></div>
<!-- 退出 -->
<div class="quitCon">
    <p>是否确认退出？</p>
    <div class="btns">
        <span>取消</span>
        <span class="mainLogOut">确定</span>
    </div>
</div>
<div class="wrap">
    <!-- 头部 -->
    <div class="nav">
        <div class="navLeft">
            <img src="/static/images/logo.png" alt="">
            <span style="font-family:KaiTi;font-weight: bold;"><h1>后台管理系统</h1></span>
        </div>
        <div class="navRight">
            <dl>
                <dt><img src="/static/images/img.jpg" alt=""></dt>
                <dd>
                    <a href="javascript:;"> <shiro:principal property="userName"/> </a>
                </dd>
            </dl>
            <span class="home" id="mainHome"><img style="height: 20px;width:20px;margin-top: 13px" src="/static/images/home.png" title="主页"></span>
            <span class="quit" id="logout"><img style="height: 27px;width:26px;margin-top: 10px" src="/static/images/logout.png" title="退出"></span>
        </div>
    </div>
    <!-- 内容 -->
    <div class="maincon">
        <!-- 左侧 -->
        <div class="mainLeft">
            <ul class="list">
                <li>
                    <shiro:hasPermission name="/system">
                        <a href="javascript:;" class="managementList">系统管理<span></span></a>
                    </shiro:hasPermission>
                    <ul class="child_menu">
                        <shiro:hasPermission name="/system/SystemUser">
                            <li targetHref="/goToHtml.do?goToHtmlUrl=/system/SystemUserList"><a href="javascript:;">用户管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/system/SystemRole">
                            <li targetHref="/goToHtml.do?goToHtmlUrl=/system/SystemRoleList"><a href="javascript:;">角色管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/system/SystemOrg">
                            <li targetHref="/goToHtml.do?goToHtmlUrl=/system/SystemOrgList"><a href="javascript:;">机构管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/system/SystemResource">
                            <li targetHref="/goToHtml.do?goToHtmlUrl=/system/SystemResourceList"><a href="javascript:;">资源管理</a></li>
                        </shiro:hasPermission>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- 右侧 -->
        <div class="mainRight">
            <p class="notice"> 当前位置：</p>
            <iframe id="iframe" src="../main_index.html" style="height: 95%;"></iframe>
        </div>
    </div>
</div>

<!--[if lte IE 7]>
<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
<![endif]-->

<style>
    /*ie6提示*/
    #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
    #ie6-warning p{width:960px;margin:0 auto;}
</style>

<script src="/static/js/jquery-3.2.1.min.js"></script>
<script src="/static/layer/layer.js"></script>
<script src="/static/js/main.js"></script>
</body>

</html>

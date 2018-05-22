<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>系统用户</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/common.css">
</head>

<body>
<input type="hidden" id="typeVal" value="list">
<div class="mainList">
    <div class="dateBig">
        <div style="height:10px">
            <ul class="uls" id="optProject">
                <shiro:hasPermission name="/systemUser/addUser">
                    <li class="default">
                        <a opt-id="add" href="javascript:;">添加用户</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemUser/editUser">
                    <li class="default">
                        <a opt-id="edit" href="javascript:;">编辑</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemUser/delUser">
                    <li class="default">
                        <a opt-id="del" href="javascript:;">删除</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemUser/roleUser">
                    <li class="default">
                        <a opt-id="role" href="javascript:;">更新角色</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemUser/pwdUser">
                    <li class="default">
                        <a opt-id="pwd" href="javascript:;">强制改密</a>
                    </li>
                </shiro:hasPermission>
            </ul>
            <ul class="detailedQw" id="selectIDVal">
                <li>
                    <input type="text" name="userName" placeholder="用户名">
                    <input type="text" name="userMobile" placeholder="手机号">
                </li>
                <li>
                    <em class="require" onclick="clickSearch()">查询</em>
                </li>
            </ul>
        </div>
        <div class="tab">
            <table>
                <thead>
                <tr>
                    <th>选择</th>
                    <th>用户名</th>
                    <th>真实姓名</th>
                    <th>所属部门</th>
                    <th>手机号</th>
                    <th>邮箱</th>
                    <th>状态</th>
                    <th>更新时间
                        <p>
                            <img src="/static/images/toptriangle.png" alt="" class="img1">
                            <img src="/static/images/botttriangle.png" alt="">
                        </p>
                    </th>
                </tr>
                </thead>
                <tbody id="dataBodyList">
                </tbody>
            </table>
        </div>
    </div>
    <div id="cmpager">
    </div>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemUser.js"></script>
<script src="/static/js/pageUtil.js"></script>
</html>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统角色</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/common.css">
</head>

<body>
<input type="hidden" id="typeVal" value="list">
<div class="mainList">
    <div class="dateBig">
        <div style="height:10px">
            <ul class="uls" id="optProject">
                <shiro:hasPermission name="/systemRole/addRole">
                    <li class="default">
                        <a opt-id="add" href="javascript:;">添加角色</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemRole/editRole">
                    <li class="default">
                        <a opt-id="edit" href="javascript:;">编辑</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemRole/authRole">
                    <li class="default">
                        <a opt-id="auth" href="javascript:;">授权</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemRole/delRole">
                    <li class="default">
                        <a opt-id="del" href="javascript:;">删除</a>
                    </li>
                </shiro:hasPermission>
            </ul>
            <ul class="detailedQw" id="selectIDVal">
                <li>
                    <input type="text" name="roleName" placeholder="角色名称">
                    <div class="searchSelect">
                        <select id="selectOrgCode" name='orgCode'></select>
                    </div>
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
                    <th>角色名称</th>
                    <th>所属机构</th>
                    <th>备注</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
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
<script src="/static/js/system/systemRole.js"></script>
<script src="/static/js/pageUtil.js"></script>
</html>

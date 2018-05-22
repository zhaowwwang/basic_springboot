<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>

<head>
    <title>系统机构</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/common.css">
</head>

<body>
<input type="hidden" id="typeVal" value="list">
<div class="mainList">
    <div class="dateBig">
        <div style="height:10px">
            <ul class="uls" id="optProject">
                <shiro:hasPermission name="/systemOrg/addOrg">
                <li class="default">
                    <a opt-id="add" href="javascript:;">添加机构</a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemOrg/editOrg">
                    <li class="default">
                        <a opt-id="edit" href="javascript:;">编辑</a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="/systemOrg/delOrg">
                    <li class="default">
                        <a opt-id="del" href="javascript:;">删除</a>
                    </li>
                </shiro:hasPermission>
            </ul>
            <ul class="detailedQw" id="selectVal">
                <li>
                    <input class="searchInput" name="orgName" type="text" placeholder="请输入机构名称">
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
                    <th>机构名称</th>
                    <th>上级机构名称</th>
                    <th>机构地址</th>
                    <th>机构代码</th>
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
<script src="/static/js/system/systemOrg.js"></script>
<script src="/static/js/pageUtil.js"></script>
</html>

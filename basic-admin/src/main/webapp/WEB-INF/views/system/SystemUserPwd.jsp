<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统用户</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/cudCommon.css">
</head>
<body>
<input type="hidden" id="typeVal" value="pwd">
<div class="div-info-main" id="pwd">
    <h3 class="div-info-tit">用户密码信息</h3>
    <table class="div-info-table">
        <tr>
            <th width="20%">用户名：</th>
            <td><input name="userName" type="text" value="" readonly></td>
        </tr>
        <tr>
            <th>用户密码：</th>
            <td><input name="password" type="password" value="" required placeholder="请输入用户密码"></td>
        </tr>
    </table>
    <a class="confirm" href="javascript:void 0;">确定</a>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemUser.js"></script>

</html>

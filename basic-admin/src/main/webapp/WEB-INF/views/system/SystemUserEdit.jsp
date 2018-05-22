<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统用户</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/cudCommon.css">
</head>
<body>
<input type="hidden" id="typeVal" value="edit">
<div class="div-info-main" id="edit">
    <h3 class="div-info-tit">用户信息</h3>
    <table class="div-info-table">
        <tr>
            <th width="20%">用户名：</th>
            <td><input name="userName" type="text" value="" required placeholder="请输入用户名"></td>
        </tr>
        <tr>
            <th>真实姓名：</th>
            <td><input name="realName" type="text" value="" required placeholder="请输入真实姓名"></td>
        </tr>
        <tr>
            <th>手机：</th>
            <td><input name="userMobile" type="text" value="" required placeholder="请输入手机号"></td>
        </tr>
        <tr>
            <th>邮箱：</th>
            <td><input name="userEmail" type="text" value="" required placeholder="请输入邮箱"></td>
        </tr>

        <tr>
            <th>所属部门：</th>
            <td>
                <div class="divLineBox">
                    <span class='spanInput' style="margin-right:0px;">
                        <input id="txtInputSelectVal" type="hidden" name="organizationId" />
                        <input style="min-width: 180px" type='text' id='txtInputSelectName' class='txtInputBoxSVR' value='' onclick='showDataListDiv()' readonly />
                    </span>
                    <span id='spanBtnSelect' class='spanBtnSelect-down' onclick='showDataListDiv()'></span>
                    <div id="selectDataDiv" class="selectDataList" style="display:none;width:178px;">
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <a class="confirm" href="javascript:void 0;">确定</a>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemUser.js"></script>

</html>

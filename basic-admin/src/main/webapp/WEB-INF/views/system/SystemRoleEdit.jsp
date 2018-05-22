<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统角色</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/cudCommon.css">
</head>
<body>
<input type="hidden" id="typeVal" value="edit">
<div class="div-info-main" id="edit">
    <h3 class="div-info-tit">角色信息</h3>
    <table class="div-info-table">
        <tr>
            <th>所属机构：</th>
            <td>
                <div class="divLineBox">
                    <span class='spanInput' style="margin-right:0px;">
                        <input type="hidden" name="orgCode" />
                        <input style="min-width: 180px" type='text'class='txtInputBoxSVR' value='' onclick='showDataListDiv(this)' readonly />
                    </span>
                    <span class='spanBtnSelect-down' onclick='showDataListDiv(this)'></span>
                    <div class="selectDataList"  style="display:none;width:178px;">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <th width="20%">角色名称：</th>
            <td><input name="roleName" type="text" value="" required placeholder="请输入角色名称"></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td><input name="remark" type="text" value="" placeholder="请输入备注"></td>
        </tr>
    </table>
    <a class="confirm" href="javascript:void 0;">确定</a>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemRole.js"></script>

</html>

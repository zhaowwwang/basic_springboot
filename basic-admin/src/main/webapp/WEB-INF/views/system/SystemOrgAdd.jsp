<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统机构</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/cudCommon.css">
</head>
<body>
<input type="hidden" id="typeVal" value="add">
<div class="div-info-main" id="add">
    <h3 class="div-info-tit">机构信息</h3>
    <table class="div-info-table">
        <tr>
            <th>上级机构：</th>
            <td>
                <div class="divLineBox">
                    <span class='spanInput' style="margin-right:0px;">
                        <input type="hidden" name="pid" />
                        <input style="min-width: 180px" type='text' class='txtInputBoxSVR' value='' onclick='showDataListDiv(this)' readonly />
                    </span>
                    <span class='spanBtnSelect-down' onclick='showDataListDiv(this)'></span>
                    <div class="selectDataList"  style="display:none;width:178px;">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <th width="20%">机构代码：</th>
            <td><input name="orgCode" type="text" value="" required placeholder="请输入机构代码"></td>
        </tr>
        <tr>
            <th>机构名称：</th>
            <td><input name="orgName" type="text" value="" required placeholder="请输入机构名称"></td>
        </tr>
        <tr>
            <th>机构地址：</th>
            <td><input name="orgAddress" type="text" value="" placeholder="请输入机构地址"></td>
        </tr>
    </table>
    <a class="confirm" href="javascript:void 0;">确定</a>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemOrg.js"></script>
</html>

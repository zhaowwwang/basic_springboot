<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统资源</title>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/cudCommon.css">
</head>
<body>
<input type="hidden" id="typeVal" value="edit">
<div class="div-info-main" id="edit">
    <h3 class="div-info-tit">资源信息</h3>
    <table class="div-info-table">
        <tr>
            <th width="20%">资源名称：</th>
            <td><input name="resourceName" type="text" value="" required placeholder="请输入资源名称"></td>
        </tr>
        <tr>
            <th>资源链接：</th>
            <td><input name="resourceUrl" type="text" value="" required placeholder="请输入资源链接"></td>
        </tr>
        <tr>
            <th>描述：</th>
            <td><input name="description" type="text" value="" placeholder="请输入描述"></td>
        </tr>
        <tr>
            <th>上级资源：</th>
            <td><select name="pid" id="optionPidHtml"></select></td>
        </tr>
        <tr>
            <th>资源状态：</th>
            <td><select name="resourceStatus" id="optionStatusHtml"></select></td>
        </tr>

        <tr>
            <th>资源类型：</th>
            <td><select name="resourceType" id="optionTypeHtml"></select></td>
        </tr>
    </table>
    <a class="confirm" href="javascript:void 0;">确定</a>
</div>
</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemResource.js"></script>

</html>

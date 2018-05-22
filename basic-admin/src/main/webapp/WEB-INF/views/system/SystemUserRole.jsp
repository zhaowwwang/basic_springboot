<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户角色</title>
    <style rel="stylesheet">
        #body table{
            width: 100%;
            border-collapse: collapse;
        }
        #body table,table tr th, table tr td { border:1px solid #BBFFBB; }
        tbody tr{
            height: 40px;
            font-size: 12px;
        }
        label{
            margin-left: 20px;
        }
        ul {
            list-style: none;
        }
        a{ display: inline-block; width: 60px; height: 30px; line-height: 30px; border-radius: 5px; text-align: center; text-decoration: none;  }
        .confirm { background: #2A3F54; color: #fff;margin-left:90%;}
    </style>
</head>
<body>
<input type="hidden" id="typeVal" value="role">
<div id="body">
    <table>
        <tbody id="authBody">
        </tbody>
    </table>
    <ul>
        <li><a class="confirm" href="javascript:void 0;">确定</a></li>
    </ul>

</div>

</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemUser.js"></script>

</html>

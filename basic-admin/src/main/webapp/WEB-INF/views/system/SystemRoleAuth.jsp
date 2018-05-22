<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色授权</title>
    <style rel="stylesheet">
        #body table{
            width: 100%;
            border-collapse: collapse;
        }
        #body table,table tr th, table tr td { border:1px solid #BBFFBB; }

        .first-menu{
            width: 18%;
            text-align: center;
            background-color: #BEBEBE;
        }
        .second-menu{
            width: 18%;
            text-align: center;
            background-color: #BEBEBE;
        }
        .thire-menu{
            width: 60%;
            text-align: center;
            background-color: #BEBEBE;
        }
        tbody tr{
            height: 40px;
            font-size: 12px;
        }
        thead tr{
            height: 40px;
            font-size: 15px;
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
<input type="hidden" id="typeVal" value="auth">
<div id="body">
    <table>
        <thead>
        <tr>
            <td class="first-menu">一级菜单</td>
            <td class="second-menu">二级菜单</td>
            <td class="thire-menu">按钮</td>
        </tr>
        </thead>
        <tbody id="authBody">
        </tbody>
    </table>
    <ul>
        <li><a class="confirm" href="javascript:void 0;">确定</a></li>
    </ul>

</div>

</body>
<jsp:include page="../common/bottomJs.jsp"/>
<script src="/static/js/system/systemRole.js"></script>

</html>

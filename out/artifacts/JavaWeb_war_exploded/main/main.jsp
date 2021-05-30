<%@ page import="com.learn.login.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: xuyicong
  Date: 2020/1/20
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="contentType" content="text/html;charset=utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.3/css/mdui.min.css">
    <script src="//www.mdui.org/source/dist/js/mdui.min.js"></script>
    <script type="text/javascript">
        var $$ = mdui.JQ;
        function logout() {
            var flag = window.confirm("退出？");
            // alert(flag);
            if (flag) {
                window.location.href = "/user?oper=out";
            }
        }
    </script>
</head>
<body>
<div class="mdui-container">
<%--    <span>用户：<%=request.getParameter("name")%></span>--%>
    <div class="mdui-table-fluid">
        <table class="mdui-table">
            <thead>
            <tr>
                <th>用户名</th>
                <th>用户id</th>
                <th>年龄</th>
                <th>生日</th>
                <th>性别</th>
                <th>密码</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%=((User)session.getAttribute("user")).getUname()%></td>
                <td><%=((User)session.getAttribute("user")).getUid()%></td>
                <td><%=((User)session.getAttribute("user")).getAge()%></td>
                <td><%=((User)session.getAttribute("user")).getBirth()%></td>
                <td><%=((User)session.getAttribute("user")).getSex()%></td>
                <td><%=((User)session.getAttribute("user")).getPwd()%></td>
            </tr>
            </tbody>
        </table>
    </div>
    <button class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-theme-accent" style="margin: 50px auto;" onclick="logout()">退出</button>
    <span>当前在线人数:${applicationScope.count}</span>
</div>
</body>
</html>

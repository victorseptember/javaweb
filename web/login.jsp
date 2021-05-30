<%@ page import="java.io.File" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %><%--
  Created by IntelliJ IDEA.
  User: xuyicong
  Date: 2019/9/18
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<%--    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.3/css/mdui.min.css">--%>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/css/mdui.min.css"
            integrity="sha384-cLRrMq39HOZdvE0j6yBojO4+1PrHfB7a9l5qLcmRm/fiWXYY+CndJPmyu5FV/9Tw"
            crossorigin="anonymous"
    />
<%--    <script src="//www.mdui.org/source/dist/js/mdui.min.js"></script>--%>
    <script
            src="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/js/mdui.min.js"
            integrity="sha384-gCMZcshYKOGRX9r6wbDrvF+TcCCswSHFucUzUPwka+Gr+uHgjlYvkABr95TCOz3A"
            crossorigin="anonymous"
    ></script>
</head>
<body class="mdui-color-theme-200">
<%--<%out.println("hepp");%>--%>

<div class="mudi-container" style="position: relative;left: 50%;top: 50%;transform: translateX(-50%) translateY(-50%);">
<div class="mdui-row" style="display: flex;justify-content: center;">
    <div class="mdui-col-xs-6">
    <form action="/user" method="post">
        <input type="hidden" name="oper" value="login">
        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">username</label>
            <input class="mdui-textfield-input" name="username" type="text"/>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">password</label>
            <input class="mdui-textfield-input" name="password" type="password"/>
        </div>
<%--        用户名：<input type="text" name="用户名" placeholder="用户名"><br/>--%>
<%--        密码：<input type="password" name="密码" placeholder="密码"><br/>--%>
<%--        <input class="mdui-textfield-input" type="submit" value="submit">--%>
        <button class="mdui-btn mdui-ripple" type="submit">submit</button>

    </form>
    </div>
</div>
</div>
</body>
</html>

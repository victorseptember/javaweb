<%@ page import="com.practice.pagination.pojo.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: xuyicong
  Date: 2020/2/22
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.3/css/mdui.min.css">
    <script src="//www.mdui.org/source/dist/js/mdui.min.js"></script>
</head>
<body class="mdui-color-theme-200">
    <div class="mdui-container">
        <div class="mdui-table-fluid">
            <table class="mdui-table">
                <thead>
                    <tr>
                        <th>用户名</th>
                        <th>用户id</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="user">
                    <tr>
                        <td>${user.uname}</td>
                        <td>${user.uid}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <a href="page?pageNumber=${pageInfo.pageNumber-1}&pageSize=${pageInfo.pageSize}">上一页</a>
        <a href="page?pageNumber=${pageInfo.pageNumber+1}&pageSize=${pageInfo.pageSize}">下一页</a>
    </div>
</body>
</html>

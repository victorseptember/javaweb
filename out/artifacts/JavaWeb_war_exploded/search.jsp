<%--
  Created by IntelliJ IDEA.
  User: xuyicong
  Date: 2021/4/7
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CipherTextRetrieve</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/css/mdui.min.css"
            integrity="sha384-cLRrMq39HOZdvE0j6yBojO4+1PrHfB7a9l5qLcmRm/fiWXYY+CndJPmyu5FV/9Tw"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/js/mdui.min.js"
            integrity="sha384-gCMZcshYKOGRX9r6wbDrvF+TcCCswSHFucUzUPwka+Gr+uHgjlYvkABr95TCOz3A"
            crossorigin="anonymous"
    ></script>
</head>
<body>
<div class="mudi-container" style="position: relative;left: 50%;top: 50%;transform: translateX(-50%) translateY(-50%);">
    <div class="mdui-row" style="display: flex;justify-content: center;">
        <div class="mdui-col-xs-6">
            <form method="post">
                <div class="mdui-textfield mdui-textfield-floating-label">
                    <label class="mdui-textfield-label">username</label>
                    <input class="mdui-textfield-input" name="username" type="text" />
                </div>
                <div class="mdui-textfield mdui-textfield-floating-label">
                    <label class="mdui-textfield-label">password</label>
                    <input class="mdui-textfield-input" name="password" type="password" />
                </div>
                <button class="mdui-btn mdui-ripple" type="submit">submit</button>
            </form>

        </div>
    </div>
</div>
</body>
</html>

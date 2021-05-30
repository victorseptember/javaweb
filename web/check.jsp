<%@ page import="java.io.File" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xuyicong
  Date: 2021/1/26
  Time: 22:33
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
<%! public byte[] md_5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return md5.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }

    }
    public String byte2str(byte[] bytes){
        int len = bytes.length;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i<len; i++) {
            byte byte0 = bytes[i];
            result.append(Integer.toBinaryString(byte0));
        }

        return result.toString().substring(0,2);
    }
    public BigInteger blindMessage(BigInteger m, BigInteger factor, BigInteger e, BigInteger n){
        BigInteger msg = m.multiply(factor.modPow(e,n)).mod(n);
        return msg;
    }
    public BigInteger unblind(BigInteger blindMsg, BigInteger e, BigInteger k_a, BigInteger n){
        BigInteger a = blindMsg.multiply(k_a.modInverse(n)).mod(n);
        BigInteger msg = a.modPow(e, n);

        return msg;
    }
    public boolean verdict(List<String> list, String msg){
        for (int i =0; i < list.size(); i++){
            System.out.println(list.get(i));  //  test
            if (list.get(i).equals(msg))
                return true;
        }
        return false;
    }
    %>

<%
    File file1 = new File("C:\\Users\\xuyicong\\eclipse-workspace\\JavaWeb\\publickey.txt");
    BufferedReader reader1 = new BufferedReader(new FileReader(file1));
    String publicKey = reader1.readLine();
//    File file2 = new File("C:\\Users\\xuyicong\\eclipse-workspace\\JavaWeb\\factor.txt");
//    BufferedReader reader2 = new BufferedReader(new FileReader(file2));
//    String factor = reader2.readLine();
    File file3 = new File("C:\\Users\\xuyicong\\eclipse-workspace\\JavaWeb\\modulus.txt");
    BufferedReader reader3 = new BufferedReader(new FileReader(file3));
    String modulus = reader3.readLine();

    //
    File file2 = new File("C:\\Users\\xuyicong\\eclipse-workspace\\JavaWeb\\factor.txt");
    BufferedReader reader2 = new BufferedReader(new FileReader(file2));
    String factor = reader2.readLine();
    BigInteger k_b = new BigInteger(factor);  //  服务端随机盲化因子
    File file4 = new File("C:\\Users\\xuyicong\\eclipse-workspace\\JavaWeb\\privateKey.txt");
    BufferedReader reader4 = new BufferedReader(new FileReader(file4));
    String privateKey = reader4.readLine();
    BigInteger d = new BigInteger(privateKey);

    BigInteger e = new BigInteger(publicKey);  //  公钥
    BigInteger n = new BigInteger(modulus);  //  模数

    Random random = new Random();
    BigInteger k_a = new BigInteger(1024,random);  //  客户端的随机盲化因子
%>
<%! String uname,passwd; %>
<body class="mdui-color-theme-200">
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
            <%
                uname = request.getParameter("username");
                passwd = request.getParameter("password");
                if (uname != null){
                    String credential = uname + passwd;
                    byte[] hash = md_5(credential);
                    String hashPrefix = byte2str(hash);  //  哈希值前缀

                    BigInteger m = new BigInteger(hash);
                    BigInteger msg = blindMessage(m,k_a,e,n);  //  盲化
                    System.out.println("msg:" + msg.toString());
                    BigInteger msg2 = blindMessage(msg, k_b, e, n);
                    System.out.println("msg2:" + msg2.toString());
                    BigInteger msg3 = msg2.modPow(d, n);
                    System.out.println("msg3:" + msg3.toString());
                    BigInteger org = unblind(msg3, e, k_a, n);
                    System.out.println("org:" + org.toString());
//                    System.out.println("org:" + org.toString());
//                    BigInteger org2 = blindMessage(m, k_b, e, n);
//                    System.out.println("org2:" + org2.toString());
            %>
            <form id="autoSubmit" action="/check" method="post">
                <script type="text/javascript">
                    setTimeout("autoSubmit.submit()",1000);  //  1s后自动提交
                </script>
                <input type="hidden" name="blindMsg" value="<%=msg.toString()%>">
                <input type="hidden" name="hashPrefix" value="<%=hashPrefix%>">
            </form>
            <%}%>
            <%if (session.getAttribute("sig") != null) {
                //检查list集合中的数据
                String sig_str = (String) session.getAttribute("sig");
                System.out.println("sig_str:" + sig_str);
                BigInteger sig = new BigInteger(sig_str);
                System.out.println("sig:"+ sig.toString());
                BigInteger un_sig = unblind(sig, e, k_a, n);
                List<String> list = (List<String>) session.getAttribute("list");
                if (list.isEmpty()) {
                    System.out.println("list is empty");
                    out.println("safe!");
                } else {
                    boolean result = verdict(list, un_sig.toString());
                    System.out.println("un_sig:" + un_sig.toString());
                    if (result == true) out.println("unsafe!");
                    else out.println("safe");
                }

            }%>

        </div>
    </div>
</div>
</body>
</html>

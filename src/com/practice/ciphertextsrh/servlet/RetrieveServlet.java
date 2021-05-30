package com.practice.ciphertextsrh.servlet;

import com.google.gson.Gson;
import com.practice.ciphertextsrh.CreateDatabase;
import com.practice.ciphertextsrh.service.SelectService;
import com.practice.ciphertextsrh.service.impl.SelectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@WebServlet(name = "RetrieveServlet")
public class RetrieveServlet extends HttpServlet {
    SelectService service = new SelectServiceImpl();
    CreateDatabase database = new CreateDatabase();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");


        //home/xyc/apache-tomcat-8.5.61/webapps/web
        //C:\Users\xuyicong\eclipse-workspace\JavaWeb\web\publickey.txt
        System.out.println("==1==");
        File file1 = new File("/home/xyc/apache-tomcat-8.5.61/webapps/web/publickey.txt");
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        String publicKey = reader1.readLine();
        File file2 = new File("/home/xyc/apache-tomcat-8.5.61/webapps/web/factor.txt");
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        String factor = reader2.readLine();
        File file3 = new File("/home/xyc/apache-tomcat-8.5.61/webapps/web/modulus.txt");
        BufferedReader reader3 = new BufferedReader(new FileReader(file3));
        String modulus = reader3.readLine();
        File file4 = new File("/home/xyc/apache-tomcat-8.5.61/webapps/web/privatekey.txt");
        BufferedReader reader4 = new BufferedReader(new FileReader(file4));
        String privateKey = reader4.readLine();

        BigInteger e = new BigInteger(publicKey);  //  公钥
        BigInteger n = new BigInteger(modulus);  //  模数
        BigInteger k_b = new BigInteger(factor);  //  盲化因子
        BigInteger d = new BigInteger(privateKey);
        String blindMsg = req.getParameter("blindMsg");
        System.out.println("blindMsg:" + blindMsg);
        BigInteger blindMsg_a = new BigInteger(blindMsg);
        String hashPrefix = req.getParameter("hashPrefix");

//        String k_a_str = req.getParameter("factor");
//        BigInteger k_a = new BigInteger(k_a_str);

        BigInteger blindMsg_ab = database.blindMessage(blindMsg_a,k_b,e,n);
        System.out.println("blindMsg_ab:" + blindMsg_ab.toString());
        BigInteger sig = blindMsg_ab.modPow(d, n);
        System.out.println("sig:" + sig.toString());

//        BigInteger a = sig.multiply(k_a.modInverse(n)).mod(n);
//        BigInteger msg = a.modPow(e, n);
//        System.out.println("msg:" + msg.toString());
        //检验哈希值前缀
        List<String> list = service.selectService(hashPrefix);
        list.add(0, sig.toString());
//        System.out.println("==2==");
        Gson gson = new Gson();
//        System.out.println("==1==");
        String json = gson.toJson(list);
//        System.out.println(json);


//        byte[] hash = database.md_5("peter789");
//        BigInteger m = new BigInteger(hash);
//        System.out.println("hash:" + m.toString());
//        BigInteger hb = database.blindMessage(m,k_b,e,n);
//        System.out.println("Hb:" + hb.toString());

        resp.getWriter().write(json);
//        boolean result = check.checkService(sig.toString());
//
//        HttpSession session = req.getSession();
//        session.setAttribute("list",list);  //  返回具有相同hash前缀的数据集合
//        session.setAttribute("sig", sig.toString());  //  返回经服务器盲化的信息

//        req.getRequestDispatcher("/check.jsp").forward(req,resp);
//        System.out.println(blindMsg);
    }

}

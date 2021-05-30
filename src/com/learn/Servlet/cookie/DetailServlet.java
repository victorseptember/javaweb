package com.learn.Servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = HomeServlet.getAll().get(id);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("书的编号："+book.getId()+"<br/>");
        printWriter.write("书的名称："+book.getName()+"<br/>");
        printWriter.write("书的作者："+book.getAuthor()+"<br/>");

        Cookie[] cookies = req.getCookies();
        String history = null;
        for (int i=0;cookies!=null&&i<cookies.length;i++){
            if (cookies[i].getName().equals("history")){
                history = cookies[i].getValue();
            }
        }

        if (history == null){   //若第一次访问，则将此次的id返回当作cookie
            history = id;
        }
        else {
            String[] idHistory = history.split("_");
            List list = Arrays.asList(idHistory);
            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.addAll(list);

            System.out.println(linkedList);

            if (linkedList.contains(id)){
                linkedList.remove(id);
                linkedList.addFirst(id);
            }
            else {
                if (linkedList.size()>=3){
                    linkedList.removeLast();
                    linkedList.addFirst(id);
                }
                else{
                    linkedList.addFirst(id);
                }
            }

            StringBuffer stringBuffer = new StringBuffer();
            for (String s : linkedList) {
                stringBuffer.append(s+"_");
            }
            history = stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
        }

        Cookie cookie = new Cookie("history",history);
        cookie.setMaxAge(30000);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

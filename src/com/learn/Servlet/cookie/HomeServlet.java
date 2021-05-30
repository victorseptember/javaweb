package com.learn.Servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HomeServlet extends HttpServlet {
    private static LinkedHashMap<String,Book> linkedHashMap = new LinkedHashMap();

    //用集合来表示一个简易的数据库
    static {
        linkedHashMap.put("1",new Book("1","javaweb","zhong"));
        linkedHashMap.put("2",new Book("2","java","fu"));
        linkedHashMap.put("3",new Book("3","oracle","cheng"));
        linkedHashMap.put("4",new Book("4","mysql","ou"));
        linkedHashMap.put("5",new Book("5","ajax","zi"));
    }

    public static LinkedHashMap<String,Book> getAll(){
        return linkedHashMap;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("所有书籍:"+"<br/>");

        Set<Map.Entry<String,Book>> entries = linkedHashMap.entrySet();
        for (Map.Entry<String,Book> bookMap : entries ) {
            Book book = bookMap.getValue();
            //resp.getWriter().write(book.getId()+"      "+book.getName()+"<br/>");
            resp.getWriter().write("<a href='http://localhost:8080/JavaWeb/detail?id="+book.getId()+"' 'target=_blank'>"+book.getName()+"</a>");
            resp.getWriter().write("<br/>");
        }

        resp.getWriter().write("您浏览过的商品："+"<br/>");
        Cookie[] cookies = req.getCookies();
        for (int i=0;cookies[i]!=null&&i<cookies.length;i++) {
            if (cookies[i].getName().equals("history")) {
                String idString = cookies[i].getValue();
                String[] ids = idString.split("_");
                for (String id : ids ){
                    Book book = linkedHashMap.get(id);
                    resp.getWriter().write(book.getName()+"<br/>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.learn.Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");

        //String[] hobbies = req.getParameterValues("hobbies");
        //String address = req.getParameter("address");
        //String hidderValue = req.getParameter("aaa");


        System.out.println(username);
        System.out.println(password);
        System.out.println(gender);

        resp.setContentType("text/html;Charset=UTF-8");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(username.getBytes("UTF-8"));
        outputStream.write(password.getBytes("UTF-8"));
        outputStream.write(gender.getBytes("UTF-8"));
        //outputStream.write(hidderValue.getBytes("UTF-8"));

        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

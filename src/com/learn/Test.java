package com.learn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class Test extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = this.getServletContext().getRealPath("/1.jpg");
        System.out.println("path:"+path);
        resp.getOutputStream().write(path.getBytes());
        FileInputStream fileInputStream = new FileInputStream(path);
        String fileName = path.substring(path.lastIndexOf("\\")+1);
        System.out.println("fileName"+fileName);
        resp.getOutputStream().write(fileName.getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        //super.doPost(req, resp);
    }
}

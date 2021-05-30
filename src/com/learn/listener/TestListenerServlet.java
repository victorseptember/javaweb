package com.learn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TestListenerServlet",urlPatterns = ("/tsListener"))
public class TestListenerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("str","111");
        req.setAttribute("str","222");
        HttpSession hs = req.getSession();
        hs.setAttribute("a", "1");
        hs.invalidate();
        ServletContext sc = this.getServletContext();
        sc.setAttribute("b", "1");
    }
}

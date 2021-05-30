package com.practice.pagination.servlet;

import com.practice.pagination.pojo.PageInfo;
import com.practice.pagination.service.ShowPageService;
import com.practice.pagination.service.impl.ShowPageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowPageServlet", urlPatterns = ("/page"))
public class ShowPageServlet extends HttpServlet {
    ShowPageService showPageService = new ShowPageServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSizeStr = req.getParameter("pageSize");
        String pageNumberStr = req.getParameter("pageNumber");
        int pageSize = 2;
        if (pageSizeStr != null && !pageSizeStr.equals("")){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int pageNumber = 1;
        if (pageNumberStr !=null && !pageNumberStr.equals("")){
            pageNumber = Integer.parseInt(pageNumberStr);
        }

        PageInfo pageInfo = showPageService.showPage(pageSize,pageNumber);
        req.setAttribute("pageInfo",pageInfo);

        req.getRequestDispatcher("/page.jsp").forward(req,resp);
    }
}

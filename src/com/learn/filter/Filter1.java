package com.learn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==过滤器初始化==");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("正在被执行");
        //设置统一编码格式
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
        //判断session是否失效
//        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
//        if (session.getAttribute("user")==null){
//            //重定向到登录页面
//            ((HttpServletResponse)servletResponse).sendRedirect("/JavaWeb/login.jsp");
//        }else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        System.out.println("通过过滤");
    }

    @Override
    public void destroy() {
        System.out.println("==过滤器被销毁==");
    }
}

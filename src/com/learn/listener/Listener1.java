package com.learn.listener;

import javax.servlet.*;
import javax.servlet.http.*;

public class Listener1 implements ServletRequestListener, HttpSessionListener, ServletContextListener,
        ServletRequestAttributeListener, HttpSessionAttributeListener, ServletContextAttributeListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("request请求被销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("request请求被初始化");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext对象被初始化");
        ServletContext sc = servletContextEvent.getServletContext();
        sc.setAttribute("count",0);
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//        System.out.println("request请求"+servletRequestAttributeEvent.getName()+"属性被更改");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//        System.out.println("");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//        System.out.println("");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
//        System.out.println("session属性值"+httpSessionBindingEvent.getName()+"被添加");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("");

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext对象被销毁");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session对象初始化");
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        int count = (int) sc.getAttribute("count");
        sc.setAttribute("count",++count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session对象被销毁");
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        int count = (int) sc.getAttribute("count");
        sc.setAttribute("count",--count);
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
//        System.out.println("");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
//        System.out.println("");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
//        System.out.println("");
    }
}

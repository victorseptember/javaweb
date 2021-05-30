package com.learn.login;

import com.learn.login.pojo.User;
import com.learn.login.service.UserService;
import com.learn.login.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        String oper = req.getParameter("oper");
        if (oper.equals("login")){  //  处理登录请求
            checkUserLogin(req,resp);

        }else if (oper.equals("reg")){  //  处理注册请求

        }else if (oper.equals("out")){  //处理退出请求
            logout(req,resp);
        }
        else{
            System.out.println("===1===");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session对象并销毁
        HttpSession hs = req.getSession();
        hs.invalidate();
        //重定向到登录页面
        resp.sendRedirect("/login.jsp");

    }

    //检查用户登录
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService = new UserServiceImpl();
        String uname = req.getParameter("username");
        String pwd = req.getParameter("password");

        System.out.println(uname);
        System.out.println(pwd);
        User u = userService.checkUserLoginService(uname, pwd);  //  检查是否存在该用户
//        resp.getWriter().write("ooooooo");
//        if (u != null) {
//            resp.getWriter().write("用户名：" + u.getUname());
//            resp.getWriter().write("密码：" + u.getPwd());
//        }

        //使用url传递参数  参数含有中文需要编码  否则会乱码
        //resp.sendRedirect("/JavaWeb/main/main.jsp?name="+ URLEncoder.encode(u.getUname(),"utf-8")+"&uid="+u.getUid());

        //通过session传递参数，设置session对象
        if (u != null){
            HttpSession session = req.getSession();
            session.setAttribute("user",u);
            resp.sendRedirect("/main/main.jsp");
            return;
        }else{
            //如果登录校验失败，请求转发登录页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
    }

}

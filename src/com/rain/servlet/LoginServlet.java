// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginServlet.java

package com.rain.servlet;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.AdminDaoT;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{

    public LoginServlet()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String text = request.getParameter("code");
        String code = (String)request.getSession().getAttribute("code");
        //AdminDao userdao = new AdminDao();
        AdminDaoT userdao = new AdminDaoT();
        boolean result;
		try {
			result = userdao.Login_verify(username, password);
			HttpSession session = request.getSession();
	        if(code.equalsIgnoreCase(text))
	        {
	            if(result)
	            {
	                AdminBean adminbean = new AdminBean();
	                AdminDaoT admindao = new AdminDaoT();
	                try {
						adminbean = admindao.getAdminInfo(username, password);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                session.setAttribute("aid", (new StringBuilder()).append("").append(adminbean.getAid()).toString());
	                session.setMaxInactiveInterval(6000);
	                if(adminbean.getStatus() == 1)
	                	
	                    response.sendRedirect("/books/index.jsp");
	                else
	                    response.sendRedirect("/books/admin.jsp");
	            } else
	            {
	                session.setAttribute("state", "\u5BC6\u7801\u9519\u8BEF");
	                response.sendRedirect("/books/login.jsp");
	            }
	        } else
	        {
	            session.setAttribute("state", "\u9A8C\u8BC1\u7801\u9519\u8BEF");
	            response.sendRedirect("/books/login.jsp");
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private static final long serialVersionUID = 1L;
}

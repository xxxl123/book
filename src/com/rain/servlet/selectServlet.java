// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   selectServlet.java

package com.rain.servlet;

import com.rain.dao.BookDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class selectServlet extends HttpServlet
{

    public selectServlet()
    {
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int tip = Integer.parseInt(request.getParameter("tip"));
        String name = request.getParameter("name");
        BookDao bookdao = new BookDao();
        ArrayList data = bookdao.getLikeList(name);
        request.setAttribute("data", data);
        String url = "";
        if(tip == 1)
            url = response.encodeURL("admin_book.jsp");
        else
            url = response.encodeURL("select.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    private static final long serialVersionUID = 1L;
}

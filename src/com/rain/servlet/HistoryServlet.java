package com.rain.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.HistoryBean;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("*.ht")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       BookDao bookdao=new BookDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、获取请求的名称 localhost:8080/hsdsx/login
		String url = request.getRequestURI();
		// 从url中截取虚拟请求路径
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		// 通过Java中的类的反射机制 根据虚拟请求名称 自动去找当前类中相同名字的方法 然后执行
		// 1、找到名字相同的方法 getClass()获取当前类 getDeclaredMethod所有方法
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 2、调用执行该方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tohistory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<HistoryBean> list = bookdao.get_HistoryListInfo2(1);
		req.setAttribute("list", list);
		System.out.println(list);
		System.out.println("112");
		req.getRequestDispatcher("/admin_history.jsp").forward(req, resp);
	}

}

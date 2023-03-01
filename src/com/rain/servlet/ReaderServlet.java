package com.rain.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.AdminDaoT;

/**
 * Servlet implementation class ReaderServlet
 */
@WebServlet("*.rd")
public class ReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminDaoT admindao = new AdminDaoT();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderServlet() {
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
	public void toadminuser(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<AdminBean> list = admindao.get_ListInfo();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_user.jsp").forward(req, resp);
	}
	public void updateUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
			
		// 修改读者的信息
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				int lend_num = Integer.parseInt(request.getParameter("lend_num"));
				int max_num = Integer.parseInt(request.getParameter("max_num"));
				int aid = Integer.parseInt(request.getParameter("aid"));
				
				admindao.updateUser(aid, username, password, name, email, phone, lend_num, max_num);
				response.sendRedirect("toadminuser.rd");
		}

	public void deleteUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		admindao.deleteUser(aid);
		response.sendRedirect("toadminuser.rd");
	}
	
	public void AddUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		// 设置编码类型
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				// 获取要添加的读者的信息
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				int lend_num = Integer.parseInt(request.getParameter("lend_num"));
				int max_num = Integer.parseInt(request.getParameter("max_num"));
			
				// 调用函数添加读者信息
				admindao.Register(username, password, name, email, phone, lend_num, max_num);
				response.sendRedirect("toadminuser.rd");
	}
}

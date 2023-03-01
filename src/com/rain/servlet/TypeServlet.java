package com.rain.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.BookBean;
import com.rain.bean.TypeBean;
import com.rain.dao.BookDao;
import com.rain.dao.TypeDao;


/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("*.tp")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      TypeDao typedao=new TypeDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
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
	public void tobooktypeshow(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<TypeBean> list = typedao.get_ListInfo();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_booktype.jsp").forward(req, resp);
	}
	protected void addBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取图书分类的名称
		String name = request.getParameter("name");
		TypeDao typedao = new TypeDao();
		// 调用函数存入图书分类信息
		typedao.addBookType(name);
		response.sendRedirect("tobooktypeshow.tp");
	}
	protected void deleteBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int tid = Integer.parseInt(request.getParameter("tid"));
		TypeDao typedao = new TypeDao();
		typedao.deleteBookType(tid);
		response.sendRedirect("tobooktypeshow.tp");
	}
	protected void updateBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		int tid = Integer.parseInt(request.getParameter("tid"));
		TypeDao typedao = new TypeDao();
		typedao.updateTypeBook(tid, name);
		response.sendRedirect("tobooktypeshow.tp");
	}

}

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
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.bean.HistoryBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class BookBorrowServlet
 */
@WebServlet("*.br")
public class BookBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       BookDao bookdao=new BookDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookBorrowServlet() {
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
	public void tobookborrow(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<HistoryBean> list = bookdao.get_HistoryListInfo2(1);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_borrow.jsp").forward(req, resp);
	}
	public void borrowBookServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BookDao bookdao = new BookDao();
		// 为了区分借书和还书的功能，设置tip，tip为1，表示借书
		int tip = Integer.parseInt(request.getParameter("tip"));
		if (tip == 1) {
			// 获取图书id
			int bid = Integer.parseInt(request.getParameter("bid"));
			int show = Integer.parseInt(request.getParameter("show"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			// 获取到存入session的aid读者id
			String aid = (String) session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			// 通过aid获取到读者的信息
			admin = admindao.get_AidInfo2(aid);
			// 将借阅记录存入数据表
			bookdao.borrowBook(bid, admin);
			if (show == 1) {
				response.sendRedirect("/books/select.jsp");
			} else {
				response.sendRedirect("/books/bdtimes.jsp");
			}
		} else {
			// 还书功能，获取借阅记录的hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			/**
			 * 还书在管理员和读者界面都有，为了区分，设置了show字段，show为1表示读者界面
			 */
			int show = Integer.parseInt(request.getParameter("show"));
			// 调用还书函数，改变status字段
			bookdao.borrowBook2(hid);
			if (show == 1) {
				response.sendRedirect("/books/borrow.jsp");
			} else {
				response.sendRedirect("/books/tobookborrow.br");
			}

		}
	}
	public void addTimeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				String endtime = request.getParameter("endtime");
				int hid = Integer.parseInt(request.getParameter("hid"));
				BookDao bookdao = new BookDao();
				bookdao.AddTime(hid, endtime);
				response.sendRedirect("tobookborrow.br");
	}

}

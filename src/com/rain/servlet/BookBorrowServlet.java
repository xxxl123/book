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
				// 1����ȡ��������� localhost:8080/hsdsx/login
				String url = request.getRequestURI();
				// ��url�н�ȡ��������·��
				String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
				// ͨ��Java�е���ķ������ ���������������� �Զ�ȥ�ҵ�ǰ������ͬ���ֵķ��� Ȼ��ִ��
				// 1���ҵ�������ͬ�ķ��� getClass()��ȡ��ǰ�� getDeclaredMethod���з���
				try {
					Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
							HttpServletResponse.class);
					// 2������ִ�и÷���
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
		// Ϊ�����ֽ���ͻ���Ĺ��ܣ�����tip��tipΪ1����ʾ����
		int tip = Integer.parseInt(request.getParameter("tip"));
		if (tip == 1) {
			// ��ȡͼ��id
			int bid = Integer.parseInt(request.getParameter("bid"));
			int show = Integer.parseInt(request.getParameter("show"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			// ��ȡ������session��aid����id
			String aid = (String) session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			// ͨ��aid��ȡ�����ߵ���Ϣ
			admin = admindao.get_AidInfo2(aid);
			// �����ļ�¼�������ݱ�
			bookdao.borrowBook(bid, admin);
			if (show == 1) {
				response.sendRedirect("/books/select.jsp");
			} else {
				response.sendRedirect("/books/bdtimes.jsp");
			}
		} else {
			// ���鹦�ܣ���ȡ���ļ�¼��hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			/**
			 * �����ڹ���Ա�Ͷ��߽��涼�У�Ϊ�����֣�������show�ֶΣ�showΪ1��ʾ���߽���
			 */
			int show = Integer.parseInt(request.getParameter("show"));
			// ���û��麯�����ı�status�ֶ�
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
